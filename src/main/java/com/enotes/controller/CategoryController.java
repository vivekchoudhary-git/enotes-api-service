package com.enotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.model.Category;
import com.enotes.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryServiceImpl;
	
	@PostMapping("/saveCategory")
	public ResponseEntity<Object> saveCategoryData(@RequestBody Category category) {
		
		Boolean isCategorySaved = categoryServiceImpl.saveCategoryDetails(category);
		
		if(isCategorySaved) {
			return new ResponseEntity<Object>("Category is saved in Database", HttpStatus.CREATED);
		}else {
		return new ResponseEntity<Object>("Category is not saved in Database", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/viewAllCategory")
	public ResponseEntity<Object> viewAllCategoryData() {
		
		List<Category> allCategoryList = categoryServiceImpl.getAllCategoryDetails();
		
		if(CollectionUtils.isEmpty(allCategoryList)) {
			
			return new ResponseEntity<>("Could not get Category List", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(allCategoryList, HttpStatus.OK);
	}
	
	
}
