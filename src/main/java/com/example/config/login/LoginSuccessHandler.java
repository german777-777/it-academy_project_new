package com.example.config.login;

import com.example.facade.user.PersonFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.util.constant.Constants.ADMIN_AUTHORITY_NAME;
import static com.example.util.constant.Constants.TEACHER_AUTHORITY_NAME;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler {

    private final PersonFacade personFacade;

    public String getAuthenticationSuccessPage(Authentication authentication) {
        UserDetails userDetails = personFacade.loadUserByUsername((String) authentication.getPrincipal());

        List<String> userAuthorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String redirectURL;

        if (userAuthorities.contains(ADMIN_AUTHORITY_NAME)) {
            redirectURL = "admin/admin_main";
        } else if (userAuthorities.contains(TEACHER_AUTHORITY_NAME)) {
            redirectURL = "teacher/teacher_main";
        } else {
            redirectURL = "student/student_main";
        }

        return redirectURL;
    }
}
