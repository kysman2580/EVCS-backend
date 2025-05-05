package com.example.evcs.exception;

public class MissingNewPasswordException extends RuntimeException {

	public MissingNewPasswordException(String message) {
		super(message);
	}

}
