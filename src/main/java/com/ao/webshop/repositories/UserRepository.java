package com.ao.webshop.repositories;

import com.ao.webshop.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByUsername(String username);
}
