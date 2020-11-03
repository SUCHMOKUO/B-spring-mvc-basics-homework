package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ErrorResponseException {

    @Override
    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "用户已存在";
    }
}
