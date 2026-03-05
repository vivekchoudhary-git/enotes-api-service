package com.enotes.exception;

public class ExistsDataException extends RuntimeException{

	public String message;
	
	public ExistsDataException(String message) {
		super(message);
	}

	
}
