package com.ao.webshop.services;

import com.ao.webshop.models.Role;
import com.ao.webshop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role){
//        if(roleRepository.findByRoleName(role.getRoleName()) != null) throw new WebshopServiceException(HttpStatus.CONFLICT, "Role already in the database.");
        return roleRepository.save(role);
    }

}
