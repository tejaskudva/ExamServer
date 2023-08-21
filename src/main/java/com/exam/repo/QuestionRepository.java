package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findByQuiz(Quiz quiz);

}
