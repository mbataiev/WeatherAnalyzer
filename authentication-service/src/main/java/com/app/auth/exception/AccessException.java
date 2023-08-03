package com.app.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessException extends RuntimeException{
    private String message;

    public AccessException(String message) {
        super(message);
        this.message = message;
    }
}
