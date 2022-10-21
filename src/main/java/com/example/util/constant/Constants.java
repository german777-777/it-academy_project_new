package com.example.util.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String[] PUBLIC_URLS_REST = {
            "/api/v1/system/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    public static final String[] PUBLIC_URL_NON_REST = {
            "/",
            "/api/start/",
            "/api/system/**",
            "/index.jsp",
            "/login.jsp",
            "/registration.jsp"
    };

    public static final String BY_ID_MESSAGE = " by id";
    public static final String BY_NAME_MESSAGE = " by name";
    public static final String BY_LOGIN_MESSAGE = " by login";
    public static final String ALREADY_EXISTING_WITH_NAME_MESSAGE = " cause already existing with this name";
    public static final String ALREADY_EXISTING_WITH_LOGIN_MESSAGE = " cause already existing with this login";
    public static final String NOT_EXISTING_WITH_THIS_ID = " cause not exists with this id";
    public static final String NOT_VALID_DATA_MESSAGE = " cause not valid data";


    public static final String STUDENT_ROLE_NAME = "STUDENT";
    public static final String TEACHER_ROLE_NAME = "TEACHER";
    public static final String ADMIN_ROLE_NAME = "ADMIN";


    public static final String INDEX_PAGE = "index";
    public static final String MESSAGE_PARAMETER = "message";
    public static final String LOGOUT_MESSAGE_VALUE = "You are logged out!";
    public static final String NOT_LOGOUT_MESSAGE_VALUE = "You are not logged out!";
    public static final String REGISTRATION_SUCCESSFUL_MESSAGE_VALUE = "Registration was successfully passed!";
}
