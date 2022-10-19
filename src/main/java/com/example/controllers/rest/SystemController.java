package com.example.controllers.rest;

import com.example.dto.rest.credentials.CredentialsRequestDto;
import com.example.dto.rest.user.system.PersonRequestCreateDto;
import com.example.facade.user.PersonFacade;
import com.example.security.jwt.JwtProvider;
import com.example.security.manager.CommonAuthenticationManager;
import lombok.RequiredArgsConstructor;
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

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/system")
@RequiredArgsConstructor
public class SystemController {
    private final PersonFacade personFacade;
    private final CommonAuthenticationManager authenticationManager;
    private final JwtProvider provider;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody PersonRequestCreateDto personDto) {
        personFacade.savePerson(personDto);
        return ResponseEntity.ok("Registration was passed successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String[]> login(@RequestBody CredentialsRequestDto credentialsDto) {
        String login = credentialsDto.login();
        String password = credentialsDto.password();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        String token = provider.generateToken(authentication.getPrincipal().toString(), authentication.getAuthorities());

        return new ResponseEntity<>(new String[]{"Authentication & Authorization were successfully passed!", token}, OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        if (logoutHandler.isInvalidateHttpSession() &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            return new ResponseEntity<>("You are logged out!", OK);
        } else {
            return new ResponseEntity<>("You are not logged out!", INTERNAL_SERVER_ERROR);
        }
    }
}