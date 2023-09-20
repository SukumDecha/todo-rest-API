package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class TodoExceptionAdvice {

    @ResponseBody()
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String todoNotFound(TodoNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TodoUnAuthenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String todoUnAuthen(TodoUnAuthenException ex) {
        return ex.getMessage();
    }
}
