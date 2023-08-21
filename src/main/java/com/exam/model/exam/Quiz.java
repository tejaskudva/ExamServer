package com.exam.model.exam;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qId;
	
	private String title;
	private String description;
	private String maxMarks;
	private Integer numberOfQuestions;
	private boolean active=false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Question> questions;
	
	public Quiz(String title, String description, String maxMarks, Integer numberOfQuestions, boolean active) {
		super();
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberOfQuestions = numberOfQuestions;
		this.active = active;
	}
	
	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
