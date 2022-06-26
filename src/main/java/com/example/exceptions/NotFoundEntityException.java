package com.example.exceptions;

import java.util.function.Supplier;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
