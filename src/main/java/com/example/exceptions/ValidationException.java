package com.example.exceptions;

import com.example.dto.exception.FieldErrorListDto;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationException extends ApiException {
    private final transient Set<ConstraintViolation<Object>> violations;

    public ValidationException(Set<ConstraintViolation<Object>> violations) {
        super(BAD_REQUEST, "Not correct data");
        this.violations = violations;
    }

    @Override
    protected Map<String, Object> getResponseBodyFields(MessageSource messageSource) {
        Map<String, Object> responseBodyFields = super.getResponseBodyFields(messageSource);
        responseBodyFields.put("errors", FieldErrorListDto.getFromConstraintViolations(violations));
        return responseBodyFields;
    }
}
