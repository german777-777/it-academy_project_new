package com.example.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class UpdateEntityException extends ApiException {
    public UpdateEntityException(Object entity) {
        super(BAD_REQUEST, "entity.not_updated", entity);
    }
}
