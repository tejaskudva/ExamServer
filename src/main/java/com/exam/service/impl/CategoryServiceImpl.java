package com.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {

		return categoryRepository.save(category);
	}

	@Override
	public int updateCategory(Category category) {
		
		return categoryRepository.updateCategory(category.getTitle(), category.getDescription(), category.getCId());
	}

	@Override
	public List<Category> getCategory() {

		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(Long cId) {

		return categoryRepository.findById(cId).get();
	}

	@Override
	public void deleteCategory(Long cId) {

		categoryRepository.deleteById(cId);		
	}

}
