package com.example.demo.exception;

public class TodoUnAuthenException extends RuntimeException{

    public TodoUnAuthenException() {
        super("Tokin Invalid");
    }
}
