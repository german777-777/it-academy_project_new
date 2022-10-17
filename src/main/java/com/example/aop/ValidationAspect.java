package com.example.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;

import com.example.exceptions.ValidationException;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAspect {
    private final Validator validator;

    @Before("@annotation(com.example.annotation.Validate)")
    public void validate(JoinPoint joinPoint) {
        Set<ConstraintViolation<Object>> violations = Arrays.stream(joinPoint.getArgs())
                .map(validator::validate)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
