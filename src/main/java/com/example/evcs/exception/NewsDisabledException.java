package com.example.evcs.exception;

public class NewsDisabledException extends RuntimeException {
    public NewsDisabledException(String message) {
        super(message);
    }
}