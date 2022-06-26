package com.example.exceptions;

public class UpdateEntityException extends RuntimeException {
    public UpdateEntityException(String message) {
        super(message);
    }
}
