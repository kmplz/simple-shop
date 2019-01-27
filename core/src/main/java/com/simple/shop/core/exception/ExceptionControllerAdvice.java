package com.simple.shop.core.exception;

import com.simple.shop.core.domain.api.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.util.stream.Collectors.joining;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationError(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage()))
                .collect(joining(", "));
        return new ResponseEntity<>(Response.fail(message), HttpStatus.BAD_REQUEST);
    }
}
