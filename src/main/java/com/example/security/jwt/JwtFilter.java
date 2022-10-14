package com.example.security.jwt;

import com.example.exceptions.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtProvider provider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = provider.resolveToken((HttpServletRequest) request);

        try {
            if (token != null && provider.validateToken(token)) {
                Authentication authentication = provider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException ex) {
            SecurityContextHolder.clearContext();
            // forward на login-page для аутентификации
            ((HttpServletResponse) response).sendError(ex.getStatus().value());
            throw new JwtAuthenticationException();
        }

        chain.doFilter(request, response);
    }
}
