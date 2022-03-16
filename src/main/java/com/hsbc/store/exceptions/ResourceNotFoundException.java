package com.hsbc.store.exceptions;

public class ResourceNotFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ResourceNotFoundException(int code, String message) {
		super(code, message);
	}

}
