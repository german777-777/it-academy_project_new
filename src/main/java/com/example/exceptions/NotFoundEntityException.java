package com.example.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundEntityException extends ApiException {

    public NotFoundEntityException(Object entity) {
        super(NOT_FOUND, "Wasn't found", entity);
    }
}
