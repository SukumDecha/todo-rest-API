package com.example.demo.exception;

public class TodoNotFoundException extends RuntimeException {
    public  TodoNotFoundException(long id) {
        super("Could not find TOdo with id=" + id);
    }
}
