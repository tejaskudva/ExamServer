package com.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {

		return quizRepository.save(quiz);
	}

	@Override
	public int updateQuiz(Quiz quiz) {

		return quizRepository.updateQuiz(quiz.getTitle(), quiz.getDescription(), quiz.getMaxMarks(), quiz.getNumberOfQuestions(), quiz.getActive(), quiz.getQId());
	}

	@Override
	public List<Quiz> getQuiz() {

		return quizRepository.findAll();
	}

	@Override
	public Quiz getQuiz(Long qId) {

		return quizRepository.findById(qId).orElse(new Quiz());
	}

	@Override
	public void deleteQuiz(Long qId) {

		quizRepository.deleteById(qId);
	}

}
