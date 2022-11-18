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
public class RoleServiceImpl implements RoleService{
    RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role, HttpServletRequest... request){
        String path = "";
        if(request.length < 0) path = String.valueOf(request[0].getRequestURL());
        if(roleRepository.findByRoleName(role.getRoleName()) != null) throw new WebshopException(HttpStatus.CONFLICT, path, "Role already in the database.");
        return roleRepository.save(role);
    }
}
