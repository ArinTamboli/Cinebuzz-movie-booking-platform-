package com.capgemini.MyWebProject.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.MyWebProject.dto.LoginDto;
import com.capgemini.MyWebProject.dto.ResponseToken;
import com.capgemini.MyWebProject.entities.Users;
import com.capgemini.MyWebProject.exceptions.UserAlreadyExistsException;
import com.capgemini.MyWebProject.security.JwtUtils;
import com.capgemini.MyWebProject.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	AuthenticationManager authenticationManager;
	UserService userService;
	PasswordEncoder passwordEncoder;
	JwtUtils jwtService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserService userService,
			PasswordEncoder passwordEncoder, JwtUtils jwtService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
		try {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		if (authentication.isAuthenticated()) {
			Users user = userService.findByNameOrEmail(loginDto.getUsername(), loginDto.getUsername());
			Map<String, Object> claims = new HashMap<>();
			claims.put("name", user.getName());
			claims.put("email", user.getEmail());
			claims.put("userid", user.getId());
			claims.put("usertype", user.getUsertype());
			String token = jwtService.generateToken(loginDto.getUsername(), claims);
			ResponseToken responseToken = new ResponseToken(token);
			return ResponseEntity.status(HttpStatus.OK).body(responseToken);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not Authorized !!");
	} 
	catch (Exception e) {
        e.printStackTrace(); // will show exact root cause
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
    }
	}
	@PostMapping("/register")
	public ResponseEntity<Users> registerUser(@RequestBody Users user) {
		if (userService.existsByName(user.getName()) || userService.existsByEmail(user.getEmail()))
			throw new UserAlreadyExistsException("Username or Email Exists !");
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
	}
}

