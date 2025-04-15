package com.SpringProj.todo.Exceptions;

public class CodeNotValidException extends RuntimeException {

    public CodeNotValidException(String message) {
        super(message);
    }
}
