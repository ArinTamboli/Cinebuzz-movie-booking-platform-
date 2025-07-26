package com.capgemini.MyWebProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.capgemini.MyWebProject.entities.Showtimes;
import com.capgemini.MyWebProject.services.ShowtimeService;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins="8")
@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {

	private final ShowtimeService service;

	@Autowired
	public ShowtimeController(ShowtimeService service) {
		this.service = service;
	}

	@GetMapping
	@PreAuthorize("hasRole('admin') or hasRole('user')")
	public ResponseEntity<List<Showtimes>> getAllShowtimes() {
		List<Showtimes> showtimes = service.getAllShowtimes();
		return ResponseEntity.status(HttpStatus.OK).body(showtimes);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('admin') or hasRole('user')")
	public ResponseEntity<Showtimes> getShowtime(@PathVariable Long id) {
		Showtimes showtime = service.getShowtimeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(showtime);
	}

	@PostMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Showtimes> createShowtime(@RequestBody Showtimes showtime) {
		Showtimes saved = service.createShowtime(showtime);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/showtimes/" + saved.getId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Showtimes> updateShowtime(@PathVariable Long id, @RequestBody Showtimes newShowtime) {
		Showtimes updated = service.updateShowtime(id, newShowtime);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
		service.deleteShowtime(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
