package com.example.exceptions;

public class CreateEntityException extends RuntimeException {
    public CreateEntityException(String message) {
        super(message);
    }
}
