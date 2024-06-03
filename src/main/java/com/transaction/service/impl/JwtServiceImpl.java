package com.transaction.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.transaction.dto.response.JwtClaimns;
import com.transaction.entity.UserAccount;
import com.transaction.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class JwtServiceImpl implements JwtService {

    private final String JWT_SECRET;
    private final String ISSUER;
    private final long JWT_EXPIRATION;

    public JwtServiceImpl(
            @Value("${transaction.shop.jwt.secret_key}") String jwtSecret,
            @Value("${transaction.shop.jwt.issuer}") String issuer,
            @Value("${transaction.jwt.expirationInSecond}") long expiration
    ){
        JWT_SECRET = jwtSecret;
        ISSUER = issuer;
        JWT_EXPIRATION = expiration;
    }

    @Override
    public String generateToken(UserAccount userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            return JWT.create()
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
                    .withIssuer(ISSUER)
                    .withSubject(userAccount.getId())
                    .withClaim("role", userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"error while creating jwt token");
        }

    }

    @Override
    public Boolean verifyJwtToken(String bearerToken) {
        try{
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            String token = parseJwt(bearerToken);
            verifier.verify(token);
            return true;
        } catch (JWTCreationException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public JwtClaimns getClaimsByToken(String bearerToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(parseJwt(bearerToken));
            return JwtClaimns.builder()
                    .userAccountId(decodedJWT.getSubject())
                    .roles(decodedJWT.getClaim("roles").asList(String.class))
                    .build();
        } catch (JWTVerificationException e){
            e.getMessage();
            return null;
        }
    }

    private String parseJwt(String token){
        if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
