package com.ao.webshop.controllers;

import com.ao.webshop.exceptions.WebshopException;
import com.ao.webshop.models.dto.AccessTokenDTO;
import com.ao.webshop.models.dto.Dto;
import com.ao.webshop.models.dto.LoginUserDTO;
import com.ao.webshop.models.AppUser;
import com.ao.webshop.models.dto.RegisterUserDTO;
import com.ao.webshop.services.AuthenticationService;
import com.ao.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDTO user, BindingResult bindingResult){
        URI path = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
        if(bindingResult.hasErrors()) throw new WebshopException(HttpStatus.BAD_REQUEST, path, "Validation error.");
        return ResponseEntity.created(path).body(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Dto> login(@RequestBody @Valid LoginUserDTO loginuserDTO, BindingResult bindingResult){
        URI path = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/login").toUriString());
        String username = loginuserDTO.getUsername();
        String password = loginuserDTO.getPassword();
        AppUser user = userService.getUserByUsername(username);
        if (user == null || !authenticationService.passwordIsCorrect(password, user.getPassword())) {
            throw new WebshopException(HttpStatus.NOT_FOUND, path, "Invalid Username or Password.");
            //HttpStatus.valueOf(404)
        }
        User userForToken = (User) userService.loadUserByUsername(username);
        String accessToken = authenticationService.getToken(userForToken);
        return ResponseEntity.ok().body(new AccessTokenDTO(accessToken));
    }
}


