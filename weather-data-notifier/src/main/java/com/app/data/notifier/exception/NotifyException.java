package com.app.data.notifier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotifyException extends RuntimeException {
    private String message;
    private Exception e;

    public NotifyException(String message) {
        super(message);
        this.message = message;
    }

    public NotifyException(String message, Exception e) {
        super(message, e);
        this.message = message;
        this.e = e;
    }
}
