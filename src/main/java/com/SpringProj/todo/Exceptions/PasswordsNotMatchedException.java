package com.SpringProj.todo.Exceptions;

public class PasswordsNotMatchedException extends RuntimeException {
    public PasswordsNotMatchedException(String message) {
        super(message);
    }
}
