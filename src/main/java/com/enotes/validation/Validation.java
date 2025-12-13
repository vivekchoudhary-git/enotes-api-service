package com.enotes.validation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.enotes.dto.CategoryDTO;
import com.enotes.exception.ValidationException;

@Component
public class Validation {

	public void categoryValidation(CategoryDTO categoryDTO) throws ValidationException {
		
		
		Map<String, Object> error = new LinkedHashMap<>();
		
		if(ObjectUtils.isEmpty(categoryDTO)) {
			throw new IllegalArgumentException("Category Object or JSON must not be null or empty");
		}else {
			
			if(ObjectUtils.isEmpty(categoryDTO.getName())) {
				error.put("name", "category name can not be null or empty");
			}else {
			
			if(categoryDTO.getName().length() < 3) {
				error.put("name", "category name characters must not be less than 3");
			}
			
            if(categoryDTO.getName().length() > 15) {
				error.put("name", "category name characters must not be greater than 10");
			}
            
            if(!categoryDTO.getName().matches("^[A-Za-z ]+$")) {
            	error.put("name", "category name must not contain special characters");
            }
            
			}
			
			if(ObjectUtils.isEmpty(categoryDTO.getDescription())) {
				error.put("description", "category description can not be null or empty");
			}else {
				
				if(categoryDTO.getDescription().length() < 11) {
					error.put("description", "category description characters must not be less than 11");
				}
				
				if(categoryDTO.getDescription().length() > 30) {
					error.put("description", "category description characters must not be greater than 30");
				}
				
			}
			
			if(!CollectionUtils.isEmpty(error)) {
				throw new ValidationException(error);
			}
			
			
		}
		
	}
	
}
