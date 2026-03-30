package com.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .csrf(c-> c.disable())
                .authorizeHttpRequests(request ->
                   request.requestMatchers("/users/**").hasRole("USER")
                           .requestMatchers("/admin/**").hasRole("ADMIN")
                           .requestMatchers("/auth/**").permitAll()
                           .requestMatchers("/authority/read/**").hasAuthority("READ")
                           .requestMatchers("/authority/write/**").hasAuthority("WRITE")
                           .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    //1. **UserDetails** – An interface that represents authenticated user data (username, password, roles, account status).
    //2. **UserDetailsService** – An interface used by Spring Security to load user data (UserDetails) by username.
    //3. **User (Spring Security)** – A built-in implementation of UserDetails that stores username, password, and authorities.

//    @Bean
//    public UserDetailsService loadPredefineCustomUsers(){
//        UserDetails user1 = User.withUsername("aditya")
//                .password("{noop}aditya")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.withUsername("sanket")
//                .password("{noop}sanket")
//                .roles("USER") //ROLE_USER treat inside authorities
//                .build();
//
//        UserDetails user3 = User
//                .withUsername("admin")
//                .password("{noop}admin123")
//                .roles("ADMIN") //ROLE_ADMIN
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2,user3);
//    }

//    PasswordEncoder bean created
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        log.info("AuthenticationManager bean created...!");
        return configuration.getAuthenticationManager();
    }
}
