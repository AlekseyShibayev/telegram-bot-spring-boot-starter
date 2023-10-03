package com.company.app.core.exception;

public class DeveloperMistakeException extends RuntimeException {

    public DeveloperMistakeException(String message) {
        super(message);
    }

    public DeveloperMistakeException(String message, Throwable cause) {
        super(message, cause);
    }

}
