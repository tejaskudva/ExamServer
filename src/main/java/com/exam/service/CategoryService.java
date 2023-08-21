package com.exam.service;

import java.util.List;

import com.exam.model.exam.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public int updateCategory(Category category);
	
	public List<Category> getCategory();
	
	public Category getCategory(Long cId);
	
	public void deleteCategory(Long cId);

}
