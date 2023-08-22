package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

import jakarta.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findByQuiz(Quiz quiz);
	
	@Modifying  
	@Transactional
	@Query("update Question q set q.content=:content, q.option1=:option1, q.option2=:option2, q.option3=:option3, q.option4=:option4, q.answer=:answer where q.questionId=:id")
	public int updateQuestion(String content, String option1, String option2, String option3, String option4, String answer, Long id);

}
