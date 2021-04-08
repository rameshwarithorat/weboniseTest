package com.fullerton.presentation.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fullerton.presentation.constants.ResultConstant;
import com.fullerton.presentation.pattern.cor.BaseAction;
import com.fullerton.presentation.service.LoginService;
import com.fullerton.presentation.utils.ResponseUtils;
import com.google.gson.Gson;

@Component("jwtgenerationAction")
public class AuthenticationAction extends BaseAction {
	//we can extend BaseAction, basically BaseAction is a Abstract class that implement IAction interface.
	
	@Autowired
	private LoginService loginService;
	
	@Value("${login.url}")
	private String loginUrl;
	
	public CustomResponse<Object> process(HttpServletRequest request, HttpServletResponse response,
			CustomResponse<Object> customResponse) {
		try {
			// perform authentication call
			CustomResponse<Object> loginResponse = this.authenticate(request, response);
			String output = null;
			String username = null;
			if (loginResponse != null) {

				String description = loginResponse.getDescription();

				if (description.equals("Login Success")) {
					output = ResultConstant.SUCCESS;
				} else {
					output = ResultConstant.FAIL;
				}
			} else {
				output = ResultConstant.FAIL;
			}

			IAction action = processHelper(output);

			CustomResponse<Object> tokenGenerationResponse = action.process(request, response, loginResponse);
			if (tokenGenerationResponse.getStatusCode() == 200) {
				return loginResponse;
			} else {
				return tokenGenerationResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.getCustomResponse(500, null, "Username or password is incorrect", request);
		}

	}
	
	private CustomResponse<Object> authenticate(HttpServletRequest request, HttpServletResponse response) {

		if (request.getParameter("type") != null && request.getParameter("type").equals("login")) {

			LoginRequest loginRequest = new LoginRequest();
			LoginData data = new LoginData();

			loginRequest.setType(request.getParameter("usertype"));
			String username = request.getParameter("username").replace(" ", "+");;
			String password = request.getParameter("password").replace(" ", "+");
			data.setEmployeeId(username);
			data.setPassword(password);
			if (loginRequest.getType().equals("db")) {
				data.setFlag("validate");
				data.setUserName(username);
				
			}
			if (!ruleRepository.ifEmployeeeExist(username)) {
				return ResponseUtils.getCustomResponse(500, null, "Unauthorized user!!!", request);
			}
			loginRequest.setData(data);

			if (loginRequest.getType().equals("db")) {
				CustomResponse<Object> loginData = loginService.login(loginRequest, loginUrl, request, response);
				Map<String, String> map = (Map<String, String>) loginData.getData();
				System.out.println("Map Data: " + map);
				String json_string = new Gson().toJson(map);

				ResponseData responseData = new Gson().fromJson(json_string, ResponseData.class);
				if (loginData.getStatusCode() == 200 && loginData.getDescription().equalsIgnoreCase("Login Success")
						&& loginRequest.getType().equals("db")) {
					return ResponseUtils.getCustomResponse(200, map, "Login Success", request);
				}

				else if (loginData.getStatusCode() == 500
						&& loginData.getDescription().equalsIgnoreCase("Invalid password")
						&& loginRequest.getType().equals("db")) {

						return ResponseUtils.getCustomResponse(500,map,
								"The username or password entered is incorrect. " ,
								request);
					}
				} 
			}

		
		return null;

	}

}
