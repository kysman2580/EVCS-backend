package com.example.evcs.exception;

public class InvalidVerificationCodeException extends RuntimeException {

	public InvalidVerificationCodeException(String message) {
		super(message);
	}

}
