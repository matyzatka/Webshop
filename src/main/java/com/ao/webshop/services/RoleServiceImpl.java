package com.ao.webshop.services;

import com.ao.webshop.exceptions.WebshopException;
import com.ao.webshop.models.Role;
import com.ao.webshop.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Role saveRole(Role role, HttpServletRequest... request) {
    String path = "";
    if (roleRepository.findByRoleName(role.getRoleName()) != null)
      throw new WebshopException(HttpStatus.CONFLICT, path, "Role already in the database.");
    return roleRepository.save(role);
  }
}
