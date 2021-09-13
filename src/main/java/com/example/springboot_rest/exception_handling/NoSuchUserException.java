package com.example.springboot_rest.exception_handling;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String message) {
        super(message);
    }
}
