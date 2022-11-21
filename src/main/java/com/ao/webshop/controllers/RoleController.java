package com.ao.webshop.controllers;

import com.ao.webshop.models.Role;
import com.ao.webshop.models.dto.Dto;
import com.ao.webshop.models.dto.RoleAssignmentDTO;
import com.ao.webshop.services.RoleService;
import com.ao.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {

  private final UserService userService;
  private final RoleService roleService;

  @PostMapping("/roles")
  public ResponseEntity<Dto> saveRole(
      @RequestBody Role role, HttpServletRequest httpServletRequest) {
    String path = httpServletRequest.getRequestURI();
    return ResponseEntity.created(URI.create(path)).body(roleService.saveRole(role));
  }

  @PostMapping("/roles/assign")
  public ResponseEntity<Dto> assignRole(@RequestBody RoleAssignmentDTO form) {
    return ResponseEntity.ok(userService.addRoleToUser(form.getUsername(), form.getRoleName()));
  }
}
