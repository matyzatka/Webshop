package com.ao.webshop.services;

import com.ao.webshop.exceptions.WebshopException;
import com.ao.webshop.exceptions.WebshopServiceException;
import com.ao.webshop.models.AppUser;
import com.ao.webshop.models.Role;
import com.ao.webshop.models.dto.RegisterUserDTO;
import com.ao.webshop.repositories.RoleRepository;
import com.ao.webshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found in the database.", username));
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        AppUser u = userRepository.findByUsername(username);
        return u;
    }

    @Override
    public AppUser saveUser(RegisterUserDTO registerUserDTO) {
        //TODO: verify fields during registration
        AppUser userDb = userRepository.findByUsername(registerUserDTO.getUsername());
//        if (userDb != null) throw new WebshopException("Username already exists.", status);
        String encodedPassword = bCryptPasswordEncoder.encode(registerUserDTO.getPassword());
        registerUserDTO.setPassword(encodedPassword);
        Role role = roleRepository.findByRoleName("ROLE_USER");
        AppUser user = new AppUser(null, registerUserDTO.getName(), registerUserDTO.getUsername(), registerUserDTO.getPassword(), new ArrayList<Role>(Arrays.asList(role)));
        return userRepository.save(user);
    }



    @Override
    public AppUser addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        if (!user.getRoles().contains(role)) user.getRoles().add(role);
//        else throw new WebshopException("User already has this role.", status, message1);
        return userRepository.save(user);
    }
}
