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

import static com.example.util.constant.Constants.AUTHENTICATION_AND_AUTHORIZATION_SUCCESSFUL_MESSAGE_VALUE;
import static com.example.util.constant.Constants.INDEX_PAGE;
import static com.example.util.constant.Constants.LOGOUT_MESSAGE_VALUE;
import static com.example.util.constant.Constants.MESSAGE_PARAMETER;
import static com.example.util.constant.Constants.NOT_LOGOUT_MESSAGE_VALUE;
import static com.example.util.constant.Constants.REGISTRATION_SUCCESSFUL_MESSAGE_VALUE;

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
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);

        String login = credentialsRequestDto.getLogin();
        String password = credentialsRequestDto.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        String token = provider.generateToken(authentication.getPrincipal().toString(), authentication.getAuthorities());

        modelAndView.addObject(MESSAGE_PARAMETER, AUTHENTICATION_AND_AUTHORIZATION_SUCCESSFUL_MESSAGE_VALUE);

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("personCreateRequestDto") PersonRequestCreateDto personRequestCreateDto) {
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);

        personFacade.savePerson(personRequestCreateDto);

        modelAndView.addObject(MESSAGE_PARAMETER, REGISTRATION_SUCCESSFUL_MESSAGE_VALUE);

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);

        ModelAndView modelAndView = new ModelAndView();

        if (logoutHandler.isInvalidateHttpSession() &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            modelAndView.setViewName(INDEX_PAGE);
            modelAndView.addObject(MESSAGE_PARAMETER, LOGOUT_MESSAGE_VALUE);
        } else {
            request.setAttribute(MESSAGE_PARAMETER, NOT_LOGOUT_MESSAGE_VALUE);
            response.sendRedirect(request.getRequestURI());
        }

        return modelAndView;
    }
}
