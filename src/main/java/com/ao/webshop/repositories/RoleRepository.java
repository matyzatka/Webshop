package com.ao.webshop.repositories;

import com.ao.webshop.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByRoleName(String roleName);
}
