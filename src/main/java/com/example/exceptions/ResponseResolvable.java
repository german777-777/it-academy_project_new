package com.example.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ResponseResolvable {
    ResponseEntity<Map<String, Object>> toResponseEntity(MessageSource messageSource);
}
