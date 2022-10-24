package com.example.controllers.non_rest.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.util.constant.Constants.ADMIN_MAIN_PAGE;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    public String goToAdminLoginPage() {
        return ADMIN_MAIN_PAGE;
    }

}
