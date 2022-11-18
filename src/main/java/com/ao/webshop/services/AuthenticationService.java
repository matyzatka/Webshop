package com.ao.webshop.services;

import com.ao.webshop.models.dto.LoginUserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    String generateToken(UserDetails userDetails);

    boolean passwordIsCorrect(String rawPassword, String encodedPassword);

    void verifyPassword(LoginUserDTO loginuserDTO);
}
