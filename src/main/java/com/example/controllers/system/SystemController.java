package com.example.controllers.system;

import com.example.dto.credentials.CredentialsRequestDto;
import com.example.dto.user.system.RegistrationRequestDto;
import com.example.model.users.Person;
import com.example.security.jwt.JwtProvider;
import com.example.security.manager.CommonAuthenticationManager;
import com.example.service.users.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
public class SystemController {
    private final PersonService personService;
    private final CommonAuthenticationManager authenticationManager;
    private final JwtProvider provider;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationRequestDto personDto) {
        if (personService.save(RegistrationRequestDto.convertToPerson(personDto))) {
            return new ResponseEntity<>("Регистрация прошла успешно!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Ошибка сервера! Регистрация прошла неуспешно!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CredentialsRequestDto credentialsDto) {
        String login = credentialsDto.login();
        String password = credentialsDto.password();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        String token = provider.generateToken(authentication.getPrincipal().toString(), authentication.getAuthorities());

        return new ResponseEntity<>(Map.of("Аутентификация и авторизация прошли успешно!", token), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        if (logoutHandler.isInvalidateHttpSession() && SecurityContextHolder.getContext().getAuthentication() == null) {
            return new ResponseEntity<>("Вы вышли из аккаунта!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Вы не вышли из аккаунта!", HttpStatus.BAD_REQUEST);
        }
    }
}
