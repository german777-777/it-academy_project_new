package com.example.controllers.non_rest.system;

import com.example.dto.non_rest.credentials.CredentialsRequestDto;
import com.example.facade.user.PersonFacade;
import com.example.security.jwt.JwtProvider;
import com.example.security.manager.CommonAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/system")
@RequiredArgsConstructor
public class LoginController {

    private final PersonFacade personFacade;
    private final CommonAuthenticationManager authenticationManager;
    private final JwtProvider provider;

    @GetMapping("/login")
    public String goToLoginPage(Model model) {
        model.addAttribute("credentialsRequestDto", new CredentialsRequestDto());
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@ModelAttribute("credentialRequestDto") CredentialsRequestDto credentialsRequestDto) {
        ModelAndView modelAndView = new ModelAndView("index");

        String login = credentialsRequestDto.getLogin();
        String password = credentialsRequestDto.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        String token = provider.generateToken(authentication.getPrincipal().toString(), authentication.getAuthorities());

        modelAndView.addObject("message", "Authentication & Authorization were successfully passed!");

        return modelAndView;
    }
}