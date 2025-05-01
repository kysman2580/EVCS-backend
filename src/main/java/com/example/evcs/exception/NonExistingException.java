package com.example.evcs.exception;

public class NonExistingException extends RuntimeException {

	public NonExistingException (String message) {
		super(message);
	}
}
