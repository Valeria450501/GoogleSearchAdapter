package com.task.google.search.adapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GoogleResponseFormatException extends RuntimeException {

    public GoogleResponseFormatException() {
        super("Google API response was not recognized");
    }
}
