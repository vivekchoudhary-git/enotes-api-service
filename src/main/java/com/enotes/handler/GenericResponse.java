package com.enotes.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {

	private HttpStatus httpStatus;
	private String status;
	private String message;
	private Object data;
	
	public ResponseEntity<Object> create() {
		
		Map<String, Object> grMap = new LinkedHashMap<>();
		grMap.put("status", status);
		grMap.put("message", message);
		
		if(!ObjectUtils.isEmpty(data)) {
			grMap.put("data", data);
		}
		
		return new ResponseEntity<>(grMap, httpStatus);
	}
	
}
