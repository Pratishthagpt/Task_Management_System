package com.assignment.TaskManagementSystem.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SpringSecurityConfig(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((request) -> request
                        .anyRequest().authenticated());

        http.cors().disable();
        http.csrf().disable();

        return http.build();
    }



}
