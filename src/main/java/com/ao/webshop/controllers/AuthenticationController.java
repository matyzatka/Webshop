package com.ao.webshop.controllers;

import com.ao.webshop.exceptions.WebshopException;
import com.ao.webshop.models.dto.AccessTokenDTO;
import com.ao.webshop.models.dto.Dto;
import com.ao.webshop.models.dto.LoginUserDTO;
import com.ao.webshop.models.dto.RegisterUserDTO;
import com.ao.webshop.services.AuthenticationService;
import com.ao.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDTO user, BindingResult bindingResult, HttpServletRequest request) {
        String path = String.valueOf(request.getRequestURL());
        if (bindingResult.hasErrors()) throw new WebshopException(HttpStatus.BAD_REQUEST, path, "Validation error.");
        return ResponseEntity.created(URI.create(path)).body(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Dto> login(@RequestBody @Valid LoginUserDTO loginuserDTO, BindingResult bindingResult, HttpServletRequest request) {
        String path = String.valueOf(request.getRequestURL());
        if (bindingResult.hasErrors()) throw new WebshopException(HttpStatus.BAD_REQUEST, path, "Validation error.");
        authenticationService.verifyPassword(loginuserDTO);
        User userForToken = (User) userService.loadUserByUsername(loginuserDTO.getUsername());
        String accessToken = authenticationService.generateToken(userForToken);
        return ResponseEntity.ok().body(new AccessTokenDTO(accessToken));
    }
}