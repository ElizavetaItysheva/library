package com.skypro.library.exception;

public class ValidationException extends RuntimeException{
    public ValidationException( String message ) {
        super(message);
    }
}
