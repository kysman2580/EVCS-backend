package com.example.evcs.exception;

public class MissingEmailException extends RuntimeException {

	public MissingEmailException(String message) {
		super(message);
	}
}
