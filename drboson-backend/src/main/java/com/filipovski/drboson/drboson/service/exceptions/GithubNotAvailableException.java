package com.filipovski.drboson.drboson.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class GithubNotAvailableException extends Exception {
    public GithubNotAvailableException(String message) {
        super(message);
    }
}
