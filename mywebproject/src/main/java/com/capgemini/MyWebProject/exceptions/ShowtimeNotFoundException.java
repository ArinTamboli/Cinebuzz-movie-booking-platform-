package com.capgemini.MyWebProject.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShowtimeNotFoundException extends RuntimeException {
	public ShowtimeNotFoundException(String message) {
		super(message);
	}
}
