package com.enotes.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.enotes.handler.GenericResponse;

public class CommonUtil {

	// for GET API Response where we show data on response (Case- Success)
	public static ResponseEntity<Object> createBuildResponse(Object data,HttpStatus httpStatus) {
		
		GenericResponse genericResponse = GenericResponse.builder().httpStatus(httpStatus)
		                                                           .status("success")
		                                                           .message("response received")
		                                                           .data(data)
		                                                           .build();
		
		return genericResponse.create();
		
	}
	
	// for POST API Response where we do not show data only show response message (Case- Success)
	public static ResponseEntity<Object> createBuildResponseMessage(String message,HttpStatus httpStatus) {
		
		GenericResponse genericResponse = GenericResponse.builder().httpStatus(httpStatus)
		                                                           .status("success")
		                                                           .message(message)
		                                                           .build();
		
		return genericResponse.create();
		
	}
	
	// When there is an error in response and need to show error in Object,List,Map format
	public static ResponseEntity<Object> createErrorResponse(Object data,HttpStatus httpStatus) {
		
		GenericResponse genericResponse = GenericResponse.builder().httpStatus(httpStatus)
		                                                           .status("Failure")
		                                                           .message("response is not received")
		                                                           .data(data)
		                                                           .build();
		
		return genericResponse.create();
		
	}
	
	// When there is an error in response and need to show error in Object,List,Map format
	public static ResponseEntity<Object> createErrorResponseMessage(String message,HttpStatus httpStatus) {
		
		GenericResponse genericResponse = GenericResponse.builder().httpStatus(httpStatus)
		                                                           .status("Failure")
		                                                           .message(message)
		                                                           .build();
		
		return genericResponse.create();
		
	}
	
}
