package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;

import jakarta.transaction.Transactional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	  @Modifying  
	  @Transactional
	  @Query("update Quiz q set q.title=:title, q.description=:description, q.maxMarks=:maxMarks, q.numberOfQuestions=:numberOfQuestions, q.active=:active where q.qId=:id")
	  public int updateQuiz(String title, String description, String maxMarks, Integer numberOfQuestions, boolean active, Long id);
	  
	  @Modifying
	  @Query("DELETE FROM Quiz a WHERE a.qId = :qId")
	  void deleteById(Long qId);
	  
	  public List<Quiz> findQuizByCategory(Category category);

}
