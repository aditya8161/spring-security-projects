package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(request ->
                request.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    //1. **UserDetails** – An interface that represents authenticated user data (username, password, roles, account status).
    //2. **UserDetailsService** – An interface used by Spring Security to load user data (UserDetails) by username.
    //3. **User (Spring Security)** – A built-in implementation of UserDetails that stores username, password, and authorities.

    @Bean
    public UserDetailsService loadPredefineCustomUsers(){
        UserDetails user1 = User.withUsername("aditya")
                .password("{noop}aditya")
                .build();

        UserDetails user2 = User.withUsername("sanket")
                .password("{noop}sanket")
                .build();

        UserDetails user3 = User.withUsername("admin")
                .password("{noop}admin123")
                .build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }

}
