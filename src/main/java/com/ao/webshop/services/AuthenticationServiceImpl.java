package com.ao.webshop.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String getToken(UserDetails userDetails) {
        Dotenv dotenv = Dotenv.load();
        Algorithm algorithm =
                Algorithm.HMAC512(
                        Objects.requireNonNull(dotenv.get("ACCESS_TOKEN_SECRET")).getBytes(StandardCharsets.UTF_8));
        long milliseconds = System.currentTimeMillis() + Long.parseLong(Objects.requireNonNull(dotenv.get("ACCESS_TOKEN_EXPIRATION")));
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(milliseconds))
                .withIssuer(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/login").toString())
                .withClaim(
                        "roles",
                        userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(algorithm);
    }

    @Override
    public boolean passwordIsCorrect(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
