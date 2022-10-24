package com.example.config;

import com.example.security.jwt.JwtConfigurer;
import com.example.security.manager.CommonAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.util.constant.Constants.PUBLIC_URLS_REST;
import static com.example.util.constant.Constants.PUBLIC_URL_NON_REST;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtConfigurer configurer;
    private final CommonAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authenticationManager(authenticationManager)
                    .authorizeRequests()
                        .antMatchers(PUBLIC_URLS_REST).permitAll()
                        .antMatchers(PUBLIC_URL_NON_REST).permitAll()
                        .anyRequest().authenticated()
                .and()
                .apply(configurer);
        return httpSecurity.build();
    }
}
