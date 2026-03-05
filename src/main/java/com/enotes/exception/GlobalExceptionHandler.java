package com.enotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllException(Exception e) {
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleNullPointerException(ResourceNotFoundException e) {
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleNullPointerException(IllegalArgumentException e) {
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleNullPointerException(ValidationException e) {
		
		return new ResponseEntity<>(e.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ExistsDataException.class)
	public ResponseEntity<Object> handleExistsDataException(ExistsDataException e){
		
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
	}
	
}
