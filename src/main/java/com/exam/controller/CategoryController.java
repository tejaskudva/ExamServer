package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public Category addCategory(@RequestBody Category category) {
		
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/getCategory")
	public List<Category> addCategory() {
		
		return categoryService.getCategory();
	}
	
	@GetMapping("/getCategory/{cId}")
	public Category addCategoryById(@PathVariable Long cId) {
		
		return categoryService.getCategory(cId);
	}
	
	@PutMapping("/updateCategory")
	public int updateCategory(@RequestBody Category category) {
		
		return categoryService.updateCategory(category);
	}
	
	@DeleteMapping("/deleteCategory/{cId}")
	public void deleteCategoryById(@PathVariable Long cId) {
		
		categoryService.deleteCategory(cId);
	}
	
	
}
