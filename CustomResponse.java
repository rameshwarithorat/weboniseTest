package com.fullerton.presentation.test;

public class CustomResponse<E> {

	private int statusCode = 200;

	private String description;

	private E data;

	private long date;

	private String requestPath;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public long getDate() {
		return System.currentTimeMillis();
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CustomResponse [statusCode=");
		stringBuilder.append(statusCode);
		stringBuilder.append(", description=");
		stringBuilder.append(description);
		stringBuilder.append(", data=");
		stringBuilder.append(data);
		stringBuilder.append(", date=");
		stringBuilder.append(date);
		stringBuilder.append(", requestPath=");
		stringBuilder.append(requestPath);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}