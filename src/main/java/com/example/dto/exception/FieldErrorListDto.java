package com.example.dto.exception;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public record FieldErrorListDto(List<FieldErrorDto> fieldErrors) {
    public static FieldErrorListDto getFromConstraintViolations(Set<ConstraintViolation<Object>> violations) {
        List<FieldErrorDto> fieldErrors = violations.stream()
                .map(FieldErrorDto::getFromConstraintViolation)
                .toList();
        return new FieldErrorListDto(fieldErrors);
    }
}
