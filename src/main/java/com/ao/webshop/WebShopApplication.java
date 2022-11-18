package com.ao.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ao.webshop.services.RoleService;
import com.ao.webshop.models.AppUser;
import com.ao.webshop.models.Role;
import com.ao.webshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import com.ao.webshop.models.dto.RegisterUserDTO;

@SpringBootApplication
public class WebShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService) {
        return args -> {
            roleService.saveRole(new Role("ROLE_USER"));
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_EMPLOYEE"));

            userService.saveUser(new RegisterUserDTO("adminAdmin", "testTest1.", "Admin admin"));
            userService.saveUser(new RegisterUserDTO( "userUser", "testTest1.", "User user"));

            userService.addRoleToUser("adminAdmin", "ROLE_ADMIN");
            userService.addRoleToUser("adminAdmin", "ROLE_EMPLOYEE");
        };
    }
}
