package com.enotes.exception;

import java.util.Map;

public class ValidationException extends Exception {

	private Map<String, Object> error;

	public ValidationException(Map<String, Object> error) {
		super();
		this.error = error;
	}

	public Map<String, Object> getError() {
		return error;
	}
	
	
	
}
