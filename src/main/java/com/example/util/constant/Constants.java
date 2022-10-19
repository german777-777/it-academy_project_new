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
            "/index.jsp",
            "/login.jsp",
            "/api/system/**",
            "/registration.jsp"
    };

    public static final String BY_ID_MESSAGE = " by id";
    public static final String BY_NAME_MESSAGE = " by name";
    public static final String BY_LOGIN_MESSAGE = " by login";
    public static final String ALREADY_EXISTING_WITH_NAME_MESSAGE = " cause already existing with this name";
    public static final String ALREADY_EXISTING_WITH_LOGIN_MESSAGE = " cause already existing with this login";
    public static final String NOT_EXISTING_WITH_THIS_ID = " cause not exists with this id";
    public static final String NOT_VALID_DATA_MESSAGE = " cause not valid data";
}
