package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;

public class LoginFailException extends ErrorResponseException {

    @Override
    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "用户名或密码错误";
    }
}
