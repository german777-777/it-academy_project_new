package com.example.exceptions;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Getter
public class ApiException extends ResponseStatusException implements ResponseResolvable {

    private final transient Object[] args;

    public ApiException(HttpStatus status, String reason, Object... args) {
        super(status, reason);
        this.args = args;
    }

    @Override
    public ResponseEntity<Map<String, Object>> toResponseEntity(MessageSource messageSource) {
        return ResponseEntity
                .status(getStatus())
                .body(getResponseBodyFields(messageSource));
    }

    protected Map<String, Object> getResponseBodyFields(MessageSource messageSource) {
        String message;
        try {
            message = messageSource.getMessage(Objects.requireNonNull(getReason()), args, Locale.getDefault());
        } catch (NoSuchMessageException ex) {
            message = getReason();
        }

        Map<String, Object> fields = new HashMap<>();
        fields.put("status", getRawStatusCode());
        fields.put("message", message);
        return fields;
    }
}
