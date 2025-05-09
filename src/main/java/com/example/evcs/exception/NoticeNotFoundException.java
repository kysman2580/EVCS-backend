package com.example.evcs.exception;

public class NoticeNotFoundException extends RuntimeException {

    public NoticeNotFoundException(String message) {
        super(message);
    }
}
