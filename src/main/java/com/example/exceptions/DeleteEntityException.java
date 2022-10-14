package com.example.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class DeleteEntityException extends ApiException {
    public DeleteEntityException(Object entity) {
        super(BAD_REQUEST, "Entity wasn't deleted", entity);
    }
}
