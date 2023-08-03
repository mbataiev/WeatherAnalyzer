package com.app.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JwtException extends RuntimeException {
    private String message;
    private Exception e;

    public JwtException(String message, Exception e) {
        super(message, e);
        this.message = message;
        this.e = e;
    }
}