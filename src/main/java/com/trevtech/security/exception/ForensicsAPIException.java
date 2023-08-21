package com.trevtech.security.exception;

import org.springframework.http.HttpStatus;

public class ForensicsAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public ForensicsAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ForensicsAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
