package com.example.evcs.exception;

public class VerificationCodeMismatchException extends RuntimeException {

	public VerificationCodeMismatchException(String message) {
		super(message);
	}

}
