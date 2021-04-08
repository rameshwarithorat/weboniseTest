package com.fullerton.presentation.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@PropertySource(value = { "classpath:application.properties" })
public class GenericService {
	
	@Value("${login.url}")
	private String loginUrl;

	@Autowired
	private AuthenticationAction authenticationAction;

	
	@Resource(name = "restTemplate")
	private RestTemplate restTemplate;

	
	public CustomResponse<Object> authenticate(RequestData requestData, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		CustomResponse<Object> req = null;

		if (request.getParameter("type") != null && request.getParameter("type").equals("login")) {
			String password = request.getParameter("password");
			String username = request.getParameter("username");
			if (password == null || password == "" || username == null || username == "") {
					return ResponseUtils.getCustomResponse.getCustomResponse(300, null, "Check input Fields", request);
			}
			

				req = authenticationAction.process(request, response, null);
				try {
					response.setContentType("application/json;charset=UTF-8");
					response.getOutputStream().write(restResponseBytes(req));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return req;

			}
 

		 
		return req;

	}
	private byte[] restResponseBytes(Object eErrorResponse) {
		String serialized = "";
		try {
			serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
		} catch (IOException e) {
			// logger
		}
		return serialized.getBytes();
	}
}
