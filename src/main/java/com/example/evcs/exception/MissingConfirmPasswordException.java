package com.example.evcs.exception;

public class MissingConfirmPasswordException extends RuntimeException {

	public MissingConfirmPasswordException(String message) {
		super(message);
	}
	
}
