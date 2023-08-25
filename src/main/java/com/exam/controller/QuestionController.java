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

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping("/add")
	public Question addQuestion(@RequestBody Question question) {
		
		return questionService.addQuestion(question);
	}
	
	@PostMapping("/addQuestions")
	public List<Question> addQuestion(@RequestBody List<Question> questions) {
		
		return questionService.addQuestions(questions);
	}
	
	@GetMapping("/getQuestion")
	public List<Question> getQuestion() {
		
		return questionService.getQuestion();
	}
	
	@GetMapping("/getQuestion/{questionId}")
	public Question addQuestionById(@PathVariable Long questionId) {
		
		return questionService.getQuestion(questionId);
	}
	
	@GetMapping("/quiz/{qId}")
	public List<Question> getQuestionsyQuiz(@PathVariable Long qId) {
		
		Quiz quiz = new Quiz();
		quiz.setQId(qId);
		
		return questionService.getQuestionsByQuiz(quiz);
	}
	
	@PutMapping("/updateQuestion")
	public Integer updateQuestion(@RequestBody Question question) {
		
		return questionService.updateQuestion(question);
	}
	
	@DeleteMapping("/deleteQuestion/{questionId}")
	public void deleteQuestionById(@PathVariable Long questionId) {
		
		questionService.deleteQuestion(questionId);
	}
	
	@PostMapping("/submitQuestions")
	public Integer calcScore(@RequestBody List<Question> questions) {
		
		return questionService.calculateScore(questions);
		
	}

}
