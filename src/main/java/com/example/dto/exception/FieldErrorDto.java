package com.example.dto.exception;

import javax.validation.ConstraintViolation;

public record FieldErrorDto(String field, Object value, String message) {

    public static FieldErrorDto getFromConstraintViolation(ConstraintViolation<Object> violation) {
        return new FieldErrorDto(violation.getPropertyPath().toString(), violation.getInvalidValue(), violation.getMessage());
    }
}
