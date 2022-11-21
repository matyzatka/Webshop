package com.ao.webshop;

import com.ao.webshop.models.Role;
import com.ao.webshop.models.dto.RegisterUserDTO;
import com.ao.webshop.repositories.RoleRepository;
import com.ao.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class WebShopApplication {

  private final RoleRepository roleRepository;
  private final UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(WebShopApplication.class, args);
  }

  @Bean
  public CommandLineRunner run() {
    return args -> {
      if (roleRepository.findAll().isEmpty()) {
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_EMPLOYEE"));

        userService.saveUser(new RegisterUserDTO("adminAdmin", "testTest1.", "Admin admin"));
        userService.saveUser(new RegisterUserDTO("userUser", "testTest1.", "User user"));

        userService.addRoleToUser("adminAdmin", "ROLE_ADMIN");
        userService.addRoleToUser("adminAdmin", "ROLE_EMPLOYEE");
      }
    };
  }
}
