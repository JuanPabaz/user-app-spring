package com.users.users_backend.config;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class TokenJwtConfig {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String CONTENT_TYPE = "application/json";
}
