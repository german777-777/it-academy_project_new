package com.example.controllers.non_rest.system;

import com.example.dto.credentials.CredentialsRequestDto;
import com.example.dto.user.system.PersonRequestCreateDto;
import com.example.facade.user.PersonFacade;
import com.example.security.jwt.JwtProvider;
import com.example.security.manager.CommonAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller(value = "nonRestSystemController")
@RequestMapping("api/system")
@RequiredArgsConstructor
public class SystemController {

    private final PersonFacade personFacade;
    private final CommonAuthenticationManager authenticationManager;
    private final JwtProvider provider;

    @GetMapping("/login")
    public String goToLoginPage(Model model) {
        model.addAttribute("credentialsRequestDto", new CredentialsRequestDto());
        return "login";
    }

    @GetMapping("/registration")
    public String goToRegistrationPage(Model model) {
        model.addAttribute("personCreateRequestDto", new PersonRequestCreateDto());
        return "registration";
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

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("personCreateRequestDto") PersonRequestCreateDto personRequestCreateDto) {
        ModelAndView modelAndView = new ModelAndView("index");

        personFacade.savePerson(personRequestCreateDto);

        modelAndView.addObject("message", "Registration was successfully passed!");

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        ModelAndView modelAndView = new ModelAndView();

        if (logoutHandler.isInvalidateHttpSession() &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            modelAndView.setViewName("index");
            modelAndView.addObject("message", "You are logged out!");
        } else {
            response.sendRedirect(request.getRequestURI());
        }

        return modelAndView;
    }
}
