package com.example.exceptions;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class JwtAuthenticationException extends ApiException {

    public JwtAuthenticationException() {
        super(UNAUTHORIZED, "Not correct token");
    }
}
