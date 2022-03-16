package com.hsbc.store.exceptions;

public class MethodNotAllowedException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MethodNotAllowedException(int code, String message) {
		super(code, message);
	}

}
