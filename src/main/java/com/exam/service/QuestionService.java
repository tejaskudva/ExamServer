package com.exam.service;

import java.util.List;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public List<Question> getQuestion();
	
	public Question getQuestion(Long questionId);
	
	public void deleteQuestion(Long questionId);
	
	public List<Question> getQuestionsByQuiz(Quiz quiz);

	public List<Question> addQuestions(List<Question> questions);

}
