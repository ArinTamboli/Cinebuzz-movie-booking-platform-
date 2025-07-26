package com.capgemini.MyWebProject.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Movies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Movie name name is mandatory")
	private String name;

	@NotBlank(message = "Genre is mandatory")
	private String genre;

	@NotBlank(message = "Language is mandatory")
	private String language;

	@NotNull(message = "Duration must be provided")
	@Min(value =60, message = "Movie should be atleast 60 minutes long")
	@Max(value =240, message = "Movie should be atleast 240 minutes long")
	private Double duration;

	public Movies() {
		super();
	}

	public Movies(Long id, String name, String genre,String language, Double duration) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.language = language;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genre=" + genre + ", language=" + language + ", duration="
				+ duration + "]";
	}

	

}

