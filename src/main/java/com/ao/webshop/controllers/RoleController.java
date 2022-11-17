package com.ao.webshop.controllers;

import com.ao.webshop.exceptions.WebshopException;
import com.ao.webshop.models.AppUser;
import com.ao.webshop.models.dto.RoleAssignmentDTO;
import com.ao.webshop.models.Role;
import com.ao.webshop.services.RoleService;
import com.ao.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
      return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }

    @PostMapping("/roles/assign")
    public ResponseEntity<Object> assignRole(@RequestBody RoleAssignmentDTO form){
        return ResponseEntity.ok().body(userService.addRoleToUser(form.getUsername(), form.getRoleName()));
    }
}
