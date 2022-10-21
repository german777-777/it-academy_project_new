package com.example.exceptions;

import com.example.dto.exception.FieldErrorListDto;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationException extends ApiException {
    private transient Set<ConstraintViolation<Object>> violations;

    public ValidationException(String message) {
        super(BAD_REQUEST, message);
    }

    public ValidationException(Set<ConstraintViolation<Object>> violations) {
        super(BAD_REQUEST, "valid.not_correct");
        this.violations = violations;
    }

    @Override
    protected Map<String, Object> getResponseBodyFields(MessageSource messageSource) {
        Map<String, Object> responseBodyFields = super.getResponseBodyFields(messageSource);
        responseBodyFields.put("errors", FieldErrorListDto.getFromConstraintViolations(violations));
        return responseBodyFields;
    }
}
