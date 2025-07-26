package com.capgemini.MyWebProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.capgemini.MyWebProject.entities.Bookings;
import com.capgemini.MyWebProject.services.BookingService;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService service;

	@Autowired
	public BookingController(BookingService service) {
		this.service = service;
	}

	@GetMapping
	@PreAuthorize("hasRole('admin') or hasRole('user')")
	public ResponseEntity<List<Bookings>> getAllBookings() {
		List<Bookings> bookings = service.getAllBookings();
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Bookings> getBooking(@PathVariable Long id) {
		Bookings booking = service.getBookingById(id);
		return ResponseEntity.status(HttpStatus.OK).body(booking);
	}

	@PostMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Bookings> createBooking(@RequestBody Bookings booking) {
		Bookings saved = service.createBooking(booking);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/bookings/" + saved.getId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Bookings> updateBooking(@PathVariable Long id, @RequestBody Bookings newBooking) {
		Bookings updated = service.updateBooking(id, newBooking);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		service.deleteBooking(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

