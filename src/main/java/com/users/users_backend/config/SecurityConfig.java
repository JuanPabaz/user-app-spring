package com.users.users_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(HttpMethod.GET,"/user","/user/page").permitAll()
                                .requestMatchers(HttpMethod.GET,"/user/{id}").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/user").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/user/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/user/{id}").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .csrf(config -> config.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

}

