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

import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/")
	public Quiz addQuiz(@RequestBody Quiz quiz) {
		
		return quizService.addQuiz(quiz);
	}
	
	@GetMapping("/getQuiz")
	public List<Quiz> addQuiz() {
		
		return quizService.getQuiz();
	}
	
	@GetMapping("/getQuiz/{qId}")
	public Quiz addQuizById(@PathVariable Long qId) {
		
		return quizService.getQuiz(qId);
	}
	
	@PutMapping("/updateQuiz")
	public int updateQuiz(@RequestBody Quiz quiz) {
		
		return quizService.updateQuiz(quiz);
	}
	
	@DeleteMapping("/deleteQuiz/{qId}")
	public void deleteQuizById(@PathVariable Long qId) {
		
		quizService.deleteQuiz(qId);
	}

}
