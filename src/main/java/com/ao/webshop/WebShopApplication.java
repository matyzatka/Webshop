package com.ao.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.ao.webshop.models.AppUser;
//import com.ao.webshop.models.Role;
//import com.ao.webshop.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import java.util.ArrayList;
//import com.ao.webshop.models.dto.RegisterUserDTO;

@SpringBootApplication
public class WebShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role("ROLE_USER"));
//            userService.saveRole(new Role("ROLE_ADMIN"));
//            userService.saveRole(new Role("ROLE_EMPLOYEE"));
//
//            userService.saveUser(new RegisterUserDTO("adminAdmin", "12345", "Admin admin"));
//            userService.saveUser(new RegisterUserDTO( "userUser", "12345", "User user"));
//
//            userService.addRoleToUser("adminAdmin", "ROLE_ADMIN");
//            userService.addRoleToUser("adminAdmin", "ROLE_EMPLOYEE");
//        };
//    }
}
