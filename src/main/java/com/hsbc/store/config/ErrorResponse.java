package com.hsbc.store.config;

public class ErrorResponse {

	int code;
	String message;

	public ErrorResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ErrorResponse() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
