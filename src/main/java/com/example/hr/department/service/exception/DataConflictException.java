package com.example.hr.department.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataConflictException extends ResponseStatusException {

	public DataConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}