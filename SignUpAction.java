package com.fullerton.presentation.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Component("signUp")
public class SignUpAction {

	@PostMapping(value = "/updateStatusDispatched")
	@ResponseBody
	public CustomResponse<Object> addNewUser(
			@RequestBody List<Register> registerList, HttpServletRequest request)
			  {
		
					CustomResponse<Object> commonResponse = null;
					String result = null;
					try {
						if(registerList.isEmpty()) {
							ResponseUtils.getCustomResponse(300, "List should not be empty", "List should not be empty", request);
						}
						else if (registerList != null && !registerList.isEmpty()) {
							//so here in addNewUser method ,user insert query and so the record will save.
							result = registerService.addNewUser(registerList);
							if (result.equals("Successfully Updated Status")) {
								ResponseUtils.getCustomResponse(200, result, "Record Added Successfully", request);
								
							} 
						} else {
							ResponseUtils.getCustomResponse(500, "Request Failed!", "Request Failed!", request);
						}
					} catch (Exception e) {
						ResponseUtils.getCustomResponse(500, "Request Failed!", "Request Failed!", request);
					}
			
			
		} 
	

}
