package com.ao.webshop.services;

import com.ao.webshop.models.Role;

import javax.servlet.http.HttpServletRequest;

public interface RoleService {
    Role saveRole(Role role, HttpServletRequest... request);
}
