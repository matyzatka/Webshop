package com.ao.webshop.config;

import com.ao.webshop.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring()
                        .antMatchers("/api/auth/register", "/api/auth/login", "/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
//        httpSecurity
//                .headers()
//                .xssProtection()
//                .and()
//                .contentSecurityPolicy("script-src 'self'; form-action 'self'")
//                .and()
//                .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)
//                .and()
//                .permissionsPolicy(
//                        permissionsPolicyConfig -> permissionsPolicyConfig.policy("geolocation=(self)"));
        httpSecurity.addFilterBefore(
                new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .authorizeRequests()
                .antMatchers("/api/roles/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
        return httpSecurity.build();
    }
}
