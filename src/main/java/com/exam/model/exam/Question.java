package com.exam.model.exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long questionId;
	@Column(length = 2000)
	private String content;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	
	@ManyToOne
	private Quiz quiz;

	public Question(String content, String option1, String option2, String option3, String option4, String answer) {
		super();
		this.content = content;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
	}

}
