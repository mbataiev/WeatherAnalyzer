package com.app.data.analytic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProcessingException extends RuntimeException {
    private String value;

    public ProcessingException(String value) {
        super(String.format("Processing error with value -> %s", value));
        this.value = value;
    }
}
