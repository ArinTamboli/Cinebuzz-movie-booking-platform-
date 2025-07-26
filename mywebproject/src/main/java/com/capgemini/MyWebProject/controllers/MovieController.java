package com.capgemini.MyWebProject.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.capgemini.MyWebProject.entities.Movies;
import com.capgemini.MyWebProject.services.MovieService;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

	private final MovieService service;

	@Autowired
	public MovieController(MovieService service) {
		this.service = service;
	}

	@GetMapping
	@PreAuthorize("hasRole('admin') or hasRole('user')")
	public ResponseEntity<List<Movies>> getAllMovies() {
		List<Movies> movies = service.getAllMovies();
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('admin') or hasRole('user')")
	public ResponseEntity<Movies> getEmployee(@PathVariable Long id) {
		Movies movie = service.getMovieById(id);
		return ResponseEntity.status(HttpStatus.OK).body(movie);
	}

	@PostMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Movies> createEmployee(@RequestBody Movies movie) {
		Movies saved = service.createMovie(movie);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/movies/" + saved.getId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Movies> updateEmployee(@PathVariable Long id, @RequestBody Movies newMovie) {
		Movies updated = service.updateMovie(id, newMovie);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.deleteMovie(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}