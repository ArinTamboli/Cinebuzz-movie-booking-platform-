package com.capgemini.MyWebProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Bookings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "UserID is mandatory")
	private Long UserID;

	@NotNull(message = "ShowID is mandatory")
	private Long ShowID;

	@NotNull(message = "SeatsBooked is mandatory")
	private Integer SeatsBooked;

	@NotNull(message = "BookingDate is mandatory")
	private LocalDate BookingDate;

	@NotNull(message = "Amount is mandatory")
	private Double Amount;

	public Bookings() {
		super();
	}

	public Bookings(Long id, Long UserID, Long ShowID, Integer SeatsBooked, LocalDate BookingDate, Double Amount) {
		super();
		this.id = id;
		this.UserID = UserID;
		this.ShowID = ShowID;
		this.SeatsBooked = SeatsBooked;
		this.BookingDate = BookingDate;
		this.Amount = Amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long UserID) {
		this.UserID = UserID;
	}

	public Long getShowID() {
		return ShowID;
	}

	public void setShowID(Long ShowID) {
		this.ShowID = ShowID;
	}

	public Integer getSeatsBooked() {
		return SeatsBooked;
	}

	public void setSeatsBooked(Integer SeatsBooked) {
		this.SeatsBooked = SeatsBooked;
	}

	public LocalDate getBookingDate() {
		return BookingDate;
	}

	public void setBookingDate(LocalDate BookingDate) {
		this.BookingDate = BookingDate;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double Amount) {
		this.Amount = Amount;
	}

	@Override
	public String toString() {
		return "Bookings [id=" + id + ", UserID=" + UserID + ", ShowID=" + ShowID + ", SeatsBooked=" + SeatsBooked
				+ ", BookingDate=" + BookingDate + ", Amount=" + Amount + "]";
	}
}

