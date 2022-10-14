package com.example.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class CreateEntityException extends ApiException {

    public CreateEntityException(Object entity) {
        super(BAD_REQUEST, "Entity wasn't created", entity);
    }
}
