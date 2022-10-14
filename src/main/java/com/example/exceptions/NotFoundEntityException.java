package com.example.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundEntityException extends ApiException {

    public NotFoundEntityException(Object entity) {
        super(HttpStatus.NOT_FOUND, "Entity wasn't found", entity);
    }
}
