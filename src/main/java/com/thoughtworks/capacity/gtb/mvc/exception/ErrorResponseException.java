package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;

public abstract class ErrorResponseException extends RuntimeException {

    abstract public HttpStatus getCode();
    abstract public String getMessage();
}
