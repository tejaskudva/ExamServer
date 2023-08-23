package com.exam.service;

import java.util.List;

import com.exam.model.exam.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public int updateQuiz(Quiz quiz);
	
	public List<Quiz> getQuiz();
	
	public Quiz getQuiz(Long qId);
	
	public void deleteQuiz(Long qId);

	public List<Quiz> getQuizByCategory(Long cid);

}
