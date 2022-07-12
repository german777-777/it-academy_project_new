package com.example.controllers.system;

import com.example.dto.credentials.CredentialsDto;
import com.example.dto.user.PersonDto;
import com.example.model.users.Person;
import com.example.security.jwt.JwtProvider;
import com.example.security.manager.CommonAuthenticationManager;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class RegistrationController {
    private final PersonService personService;
    private final CommonAuthenticationManager authenticationManager;
    private final JwtProvider provider;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody PersonDto personDto) {
        if (personService.save(PersonDto.convertToPerson(personDto))) {
            return new ResponseEntity<>("Регистрация прошла успешно!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Ошибка сервера! Регистрация прошла неуспешно!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDto credentialsDto) {
        String login = credentialsDto.getLogin();
        String password = credentialsDto.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        Person person = personService.findByLogin(login);

        String token = provider.generateToken(person.getLogin(), person.getRoles());

        return new ResponseEntity<>(Map.of("Аутентификация и авторизация прошли успешно!", token), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        if (logoutHandler.isInvalidateHttpSession() && SecurityContextHolder.getContext().getAuthentication() == null) {
            return new ResponseEntity<>("Вы вышли из аккаунта!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Вы не вышли из аккаунта!", HttpStatus.BAD_REQUEST);
        }
    }
}
