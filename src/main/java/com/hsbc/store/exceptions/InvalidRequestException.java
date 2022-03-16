package com.hsbc.store.exceptions;

public class InvalidRequestException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidRequestException(int code, String message) {
		super(code, message);
	}

	
}
