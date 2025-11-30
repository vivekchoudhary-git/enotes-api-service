package com.enotes.service;

import java.util.List;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.CategoryResponse;
import com.enotes.model.Category;

public interface CategoryService {

	public Boolean saveCategoryDetails(CategoryDTO categoryDTO);
	public List<CategoryDTO> getAllCategoryDetails();
	public List<CategoryResponse> getAllActiveCategoryDetails();
	
}
