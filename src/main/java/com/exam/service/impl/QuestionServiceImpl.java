package com.exam.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.repo.QuizRepository;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuizRepository quizRepository;

	@Override
	public Question addQuestion(Question question) {
		
		return questionRepository.save(question);
	}

	@Override
	public Integer updateQuestion(Question question) {
		
		return questionRepository.updateQuestion(question.getContent(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4(), question.getAnswer(), question.getQuestionId());
	}

	@Override
	public List<Question> getQuestion() {

		return questionRepository.findAll();
	}

	@Override
	public Question getQuestion(Long questionId) {

		return questionRepository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long questionId) {

		questionRepository.deleteById(questionId);
	}

	@Override
	public List<Question> getQuestionsByQuiz(Quiz quiz) {
		
		Long qId = quiz.getQId();
		quiz = quizRepository.findById(qId).get();
		
		List<Question> questions = questionRepository.findByQuiz(quiz);
		
		Collections.shuffle(questions);
		
		if(quiz.getNumberOfQuestions() < questions.size()) {
			return questions.subList(0, (quiz.getNumberOfQuestions()-1));
			
		} else {
			return questions;
		}
		
	}
	
	//ADD LIST OF QUESTIONS FOR INITIAL POSTING
	@Override
	public List<Question> addQuestions(List<Question> questions) {
		
		List<Question> check = new ArrayList<Question>();
		
		try {
			for (Question question : questions) {
				check.add(questionRepository.save(question));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public Integer calculateScore(List<Question> questions) {
		try {
			int score = 0;
			
			for(Question question: questions) {
				if(question.getChoice() != null && question.getChoice().equals(question.getAnswer())) {
					score++;
				}
			}
			return score;
			
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
