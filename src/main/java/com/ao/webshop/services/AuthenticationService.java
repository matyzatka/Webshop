package com.ao.webshop.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    String getToken(UserDetails userDetails);

    boolean passwordIsCorrect(String rawPassword, String encodedPassword);
}
