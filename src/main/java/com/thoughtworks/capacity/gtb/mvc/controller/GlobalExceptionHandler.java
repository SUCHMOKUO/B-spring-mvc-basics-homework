package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.dto.ErrorResponse;
import com.thoughtworks.capacity.gtb.mvc.exception.ErrorResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidParams(MethodArgumentNotValidException ex) {
        int code = HttpStatus.BAD_REQUEST.value();

        BindingResult result = ex.getBindingResult();

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return new ErrorResponse(code, fieldError.getDefaultMessage());
            }
        }

        return new ErrorResponse(code, "参数错误");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleInvalidParams(ConstraintViolationException ex) {
        int code = HttpStatus.BAD_REQUEST.value();

        String message = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse(ex.getMessage());
        return new ErrorResponse(code, message);
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErrorResponse> handleError(ErrorResponseException ex) {
        int code = ex.getCode().value();
        String message = ex.getMessage();

        return ResponseEntity
                .status(code)
                .body(new ErrorResponse(code, message));
    }
}
