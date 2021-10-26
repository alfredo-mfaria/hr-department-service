package com.example.hr.department.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GenericException extends ResponseStatusException {

	public GenericException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}