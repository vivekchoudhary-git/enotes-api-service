package com.enotes.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.CategoryResponse;
import com.enotes.model.Category;
import com.enotes.service.CategoryService;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryServiceImpl;
	
	@PostMapping("/saveCategory")
	public ResponseEntity<Object> saveCategoryData(@RequestBody CategoryDTO categoryDTO) {
		
		Boolean isCategorySaved = categoryServiceImpl.saveCategoryDetails(categoryDTO);
		
		if(isCategorySaved) {
			return new ResponseEntity<Object>("Category is saved in Database", HttpStatus.CREATED);
		}else {
		return new ResponseEntity<Object>("Category is not saved in Database", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/viewAllCategory")
	public ResponseEntity<Object> viewAllCategoryData() {
		
		List<CategoryDTO> allCategoryDTOList = categoryServiceImpl.getAllCategoryDetails();
		
		if(CollectionUtils.isEmpty(allCategoryDTOList)) {
			
			return new ResponseEntity<>("Could not get Category List", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(allCategoryDTOList, HttpStatus.OK);
	}
	
	
	@GetMapping("/allActiveCategory")
	public ResponseEntity<Object> viewAllActiveCategoryData() {
		
		List<CategoryResponse> activeCategoryRespList = categoryServiceImpl.getAllActiveCategoryDetails();
		
		if(CollectionUtils.isEmpty(activeCategoryRespList)) {
			
			return new ResponseEntity<Object>("Could not get active Category List", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(activeCategoryRespList, HttpStatus.OK);
	}
	
	
	@GetMapping("/viewCategory/{id}")
	public ResponseEntity<Object> viewCategoryData(@PathVariable Integer id) {
		
		CategoryDTO categoryDTO = categoryServiceImpl.getCategoryDetails(id);
		
		if(ObjectUtils.isEmpty(categoryDTO)){
			
			return new ResponseEntity<Object>("Could not get Category Data", HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			
			return new ResponseEntity<Object>(categoryDTO, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<Object> deleteCategoryData(@PathVariable Integer id) {
		
		Boolean catStatus = categoryServiceImpl.deleteCategoryDetails(id);
		
		if(catStatus) {
			return new ResponseEntity<>("Category is Deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Category is Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
