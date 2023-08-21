package com.exam.model.exam;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cId;
	private String title;
	private String description;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Quiz> quizzes;
	
	public Category(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	

}
