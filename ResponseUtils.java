package com.fullerton.presentation.test;

import javax.servlet.http.HttpServletRequest;

import com.fullerton.presentation.model.CustomResponse;

public class ResponseUtils {
	public static CustomResponse<Object> getCustomResponse(int statusCode, Object data, String description,
			HttpServletRequest request) {
		CustomResponse<Object> customResponse = new CustomResponse<Object>();
		customResponse.setData(data);
		customResponse.setDate(System.currentTimeMillis());
		customResponse.setDescription(description);
		customResponse.setStatusCode(statusCode);
		customResponse.setRequestPath(null == request.getServletPath() ? "" : request.getServletPath());
		return customResponse;
	}

	private ResponseUtils() {
	}

}
