package com.capgemini.MyWebProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.capgemini.MyWebProject.entities.Users;
import com.capgemini.MyWebProject.services.UserService;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = service.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Users> getUser(@PathVariable Long id) {
		Users user = service.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<?> createUser(@RequestBody Users user) {
		if (service.existsByEmail(user.getEmail())) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Email already in use");
	    }
		Users saved = service.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/users" + saved.getId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users newUser) {
		Users updated = service.updateUser(id, newUser);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

