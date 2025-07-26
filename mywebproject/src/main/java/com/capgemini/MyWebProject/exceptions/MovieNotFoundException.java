package com.capgemini.MyWebProject.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {
	public MovieNotFoundException(String message) {
		super(message);
	}
}

