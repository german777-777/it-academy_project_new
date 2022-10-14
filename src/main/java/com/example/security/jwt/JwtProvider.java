package com.example.security.jwt;

import com.example.exceptions.JwtAuthenticationException;
import com.example.service.users.PersonService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final PersonService personService;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long expired;

    @Value("${jwt.token.header}")
    private String header;

    @PostConstruct
    public void init() {
        this.secret = Base64.getEncoder().encodeToString(this.secret.getBytes());
    }

    public String generateToken(String login, Collection<? extends GrantedAuthority> authorities) {
        Claims claims = Jwts.claims().setSubject(login);
        authorities.forEach(authority -> claims.put(authority.getAuthority(), authority.getAuthority()));

        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + expired);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new JwtAuthenticationException();
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            UserDetails userDetails = personService.loadUserByUsername(getLogin(token));
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new JwtAuthenticationException();
        }
    }

    private String getLogin(String token) throws JwtException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(header);
    }
}
