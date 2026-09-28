package com.blood.bank.config;

import com.blood.bank.entity.User;
import com.blood.bank.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .httpBasic(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/",
                    "/index.html",
                    "/login.html",
                    "/donors.html",
                    "/inventory.html",
                    "/requests.html",
                    "/users.html",
                    "/settings.html",
                    "/style.css",
                    "/js/**"
                ).permitAll()

                .requestMatchers(
                    "/users/login",
                    "/users/register",
                    "/users/change-password"
                ).permitAll()

                .requestMatchers("/users/**")
                .hasRole("ADMIN")

                .anyRequest()
                .authenticated()
            );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository) {

        return username -> {

            User user = userRepository
                    .findByUsername(username)
                    .orElseThrow(
                        () -> new UsernameNotFoundException(
                            "User not found"
                        )
                    );

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };
    }
}