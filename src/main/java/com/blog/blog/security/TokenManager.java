package com.blog.blog.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.blog.blog.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenManager {
    @Value("${api.security.token.jwt.secret}")
    private String TOKEN_SECRET;
    final static String OFFSET_ID = "-03:00";
    final static String ISSUER = "Blog Api";

    public String generateToken(User user){
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresDate())
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Erro ao gerar token JWT", jwtCreationException);
        }

    }

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }
    private static Instant expiresDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(OFFSET_ID));
    }
}
