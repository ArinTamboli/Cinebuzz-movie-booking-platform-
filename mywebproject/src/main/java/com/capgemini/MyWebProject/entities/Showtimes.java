package com.capgemini.MyWebProject.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Showtimes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "MovieID is mandatory")
	private Long MovieID;

	@NotNull(message = "ShowDate is mandatory")
	private LocalDate ShowDate;

	@NotNull(message = "Time is mandatory")
	private LocalTime Time;

	@NotBlank(message = "Theater is mandatory")
	private String Theater;

	@NotNull(message = "AvailableSeats must be provided")
	@Min(value = 0, message = "Seats cannot be negative")
	@Max(value = 500, message = "Seats cannot exceed 500")
	private Integer AvailableSeats;

	public Showtimes() {
		super();
	}

	public Showtimes(Long id, Long MovieID, LocalDate ShowDate, LocalTime Time, String Theater, Integer AvailableSeats) {
		super();
		this.id = id;
		this.MovieID = MovieID;
		this.ShowDate = ShowDate;
		this.Time = Time;
		this.Theater = Theater;
		this.AvailableSeats = AvailableSeats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMovieID() {
		return MovieID;
	}

	public void setMovieID(Long MovieID) {
		this.MovieID = MovieID;
	}

	public LocalDate getShowDate() {
		return ShowDate;
	}

	public void setShowDate(LocalDate ShowDate) {
		this.ShowDate = ShowDate;
	}

	public LocalTime getTime() {
		return Time;
	}

	public void setTime(LocalTime Time) {
		this.Time = Time;
	}

	public String getTheater() {
		return Theater;
	}

	public void setTheater(String Theater) {
		this.Theater = Theater;
	}

	public Integer getAvailableSeats() {
		return AvailableSeats;
	}

	public void setAvailableSeats(Integer AvailableSeats) {
		this.AvailableSeats = AvailableSeats;
	}

	@Override
	public String toString() {
		return "Showtimes [id=" + id + ", MovieID=" + MovieID + ", ShowDate=" + ShowDate + ", Time=" + Time
				+ ", Theater=" + Theater + ", AvailableSeats=" + AvailableSeats + "]";
	}
}

