package com.enotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public List<Category> findByIsActiveTrueAndIsDeletedFalse();
	
}
