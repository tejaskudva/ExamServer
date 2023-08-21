package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.model.exam.Category;

import jakarta.transaction.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	  @Modifying	  
	  @Transactional	  
	  @Query("update Category c set c.title=:title, c.description=:description where c.cId=:id")
	  public int updateCategory(String title, String description, Long id);
	 

}
