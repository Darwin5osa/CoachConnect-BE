package com.digitalhouse.CoachConnectBE.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    //@Value("${JWT_SECRET_KEY}")
    //private String key; todo se utilizara cuando se puedan ingresar variables de entorno
    private final String key = "Z3VpMjNlJmM0c1R!QlN^YXpXfGVF2UjR5";

    public String generateToken(String username, String role, String nombre, String apellido, String email) {
        long now = System.currentTimeMillis();
        long TOKEN_DURATION = 900000;
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("nombre", nombre)
                .claim("apellido", apellido)
                .claim("email", email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + TOKEN_DURATION))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String validateTokenAndGetUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8))).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}

