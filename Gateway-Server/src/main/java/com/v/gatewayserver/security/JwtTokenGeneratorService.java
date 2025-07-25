//package com.v.gatewayserver.security;
//
//import java.nio.charset.StandardCharsets;
//import java.sql.Date;
//import java.time.Instant;
//import java.util.UUID;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtTokenGeneratorService {
//
//    @Value("${keycloak.client-id}")
//    private String clientId;
//
//    @Value("${keycloak.token-url}")
//    private String tokenUrl;
//
//    @Value("${keycloak.client-secret}")
//    private String clientSecret;
//
//    public String generateSignedJwt() {
//        Instant now = Instant.now();
//
//        return Jwts.builder()
//                .setIssuer(clientId)
//                .setSubject(clientId)
//                .setAudience(tokenUrl)
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plusSeconds(300)))
//                .signWith(Keys.hmacShaKeyFor(clientSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
//                .compact();
//    }
//}
