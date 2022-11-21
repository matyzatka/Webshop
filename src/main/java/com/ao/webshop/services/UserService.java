package com.ao.webshop.services;

import com.ao.webshop.models.AppUser;
import com.ao.webshop.models.dto.RegisterUserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
  AppUser getUserByUsername(String username);

  UserDetails loadUserByUsername(String username);
  // TODO: add paging to getUsers - we do not want to get thousands of users
  AppUser saveUser(RegisterUserDTO registerUserDTO);

  AppUser addRoleToUser(String username, String roleName, HttpServletRequest... request);
}
