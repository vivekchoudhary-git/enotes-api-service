package com.enotes.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.enotes.model.Category;
import com.enotes.repository.CategoryRepo;
import com.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Boolean saveCategoryDetails(Category category) {
		
		category.setIsActive(true);
		category.setIsDeleted(false);
		category.setCreatedOn(new Date());
		category.setCreatedBy(2);                   // currently we are hard coding createdBy manually only for testing.
		
		Category savedCategory = categoryRepo.save(category);
		
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<Category> getAllCategoryDetails() {
		
		List<Category> allCategoryList = categoryRepo.findAll();
		
		return allCategoryList;
	}

}
