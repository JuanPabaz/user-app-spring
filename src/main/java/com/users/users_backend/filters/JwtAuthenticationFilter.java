package com.users.users_backend.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.users_backend.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.users.users_backend.config.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = null;
        String password = null;
        try {
            UserEntity userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = userEntity.getUsername();
            password = userEntity.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

        Claims claims = Jwts
                .claims()
                .add("authorities",new ObjectMapper().writeValueAsString(authorities))
                .build();

        String jwt = Jwts.builder()
                .subject(username)
                .signWith(SECRET_KEY)
                .issuedAt(new Date())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
        response.addHeader(HEADER_AUTHORIZATION,TOKEN_PREFIX + jwt);
        Map<String, String> body = new HashMap<>();
        body.put("token", jwt);
        body.put("username", username);
        body.put("message","Sesión iniciada exitosamente");
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
