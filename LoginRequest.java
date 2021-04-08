package com.fullerton.presentation.test;

import com.fullerton.presentation.model.LoginData;

public class LoginRequest {
	private String type;
	private LoginData data;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LoginData getData() {
		return data;
	}
	public void setData(LoginData data) {
		this.data = data;
	}
	
}
