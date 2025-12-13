package com.enotes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.CategoryResponse;
import com.enotes.exception.ResourceNotFoundException;
import com.enotes.model.Category;
import com.enotes.repository.CategoryRepo;
import com.enotes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Boolean saveCategoryDetails(CategoryDTO categoryDTO) {
		
		Category category = modelMapper.map(categoryDTO, Category.class);
		
		category.setIsActive(true);
		category.setIsDeleted(false);
		category.setCreatedOn(new Date());
		category.setCreatedBy(3);                   // currently we are hard coding createdBy manually only for testing.
		
		Category savedCategory = categoryRepo.save(category);
		
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<CategoryDTO> getAllCategoryDetails() {
		
		List<Category> allCategoryList = categoryRepo.findAll();
		
		// Old traditional way of doing this (S)
		
//		ArrayList<CategoryDTO> allCategoryDTOList = new ArrayList<>();
//		
//		for(Category cat: allCategoryList) {
//			
//			CategoryDTO catDTO = modelMapper.map(cat, CategoryDTO.class);
//			
//			allCategoryDTOList.add(catDTO);
//		}
			
		// Old traditional way of doing this (E)
		
		// New Modern way of doing this (S)
		List<CategoryDTO> allCategoryDTOList = allCategoryList.stream().map(cat -> modelMapper.map(cat, CategoryDTO.class)).toList();
		// New Modern way of doing this (E)
		
		return allCategoryDTOList;
	}

	@Override
	public List<CategoryResponse> getAllActiveCategoryDetails() {
		
		List<Category> activeCategoryList = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
		
		List<CategoryResponse> catRespList = activeCategoryList.stream().map(cat -> modelMapper.map(cat, CategoryResponse.class)).toList();
		
		return catRespList;
	}

	@Override
	public CategoryDTO getCategoryDetails(Integer id) throws ResourceNotFoundException {
		
		Category category = categoryRepo.findByIdAndIsDeletedFalse(id);
		
	    if(!ObjectUtils.isEmpty(category)) {
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		return categoryDTO;
	    }else { 
	    	throw new ResourceNotFoundException("Resource Not Found for Id "+id);
	    }
	}

	@Override
	public Boolean deleteCategoryDetails(Integer id) {
		
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		
		if(optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			category.setIsDeleted(true);
			Category savedCategory = categoryRepo.save(category);
			
			if(!ObjectUtils.isEmpty(savedCategory)) {
				return true;
			}
			
			return false;
		}else {
			
			return false;
		}
		
	}

	@Override
	public Boolean updateCategoryDetails(CategoryDTO categoryDTO) {
		
		Optional<Category> existingOptionalCategory = categoryRepo.findById(categoryDTO.getId());
		
		if(existingOptionalCategory.isPresent()) {
			Category existingCategory = existingOptionalCategory.get();
			categoryDTO.setCreatedBy(existingCategory.getCreatedBy());
			categoryDTO.setCreatedOn(existingCategory.getCreatedOn());
			categoryDTO.setUpdatedBy(1);                                      // hardcoded updatedBy for testing only
			categoryDTO.setUpdatedOn(new Date());
			categoryDTO.setIsActive(existingCategory.getIsActive());
			categoryDTO.setIsDeleted(existingCategory.getIsDeleted());
			
			Category category = modelMapper.map(categoryDTO, Category.class);
			Category updatedCategory = categoryRepo.save(category);
			
			if(!ObjectUtils.isEmpty(updatedCategory)) {
				return true;
			}
			else
				
				return false;
		}
		
		return null;
		
	}

	
	
}
