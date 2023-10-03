package ru.kata.spring.boot_security.demo.rest_exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
