package com.example.exceptions;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends ApiException {

    public JwtAuthenticationException(Object entity) {
        super(HttpStatus.UNAUTHORIZED, "Not correct credentials", entity);
    }
}
