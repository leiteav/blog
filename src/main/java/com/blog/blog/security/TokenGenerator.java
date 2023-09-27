package com.blog.blog.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.blog.blog.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenGenerator {
    @Value("${api.security.token.jwt.secret}")
    private static String TOKEN_SECRET;
    final static String OFFSET_ID = "-03:00";

    public String generateToken(User user){
        try {
            return JWT.create()
                    .withIssuer("Blog API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresDate())
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Erro ao gerar token JWT", jwtCreationException);
        }

    }

    private static Instant expiresDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(OFFSET_ID));
    }
}
