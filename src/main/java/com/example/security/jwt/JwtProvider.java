package com.example.security.jwt;

import com.example.model.users.Person;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final PersonService<Person> personService;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long expired;

    @Value("${jwt.token.header}")
    private String header;

    
}
