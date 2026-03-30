package com.demo.controller;

import com.demo.entity.LoginRequest;
import com.demo.entity.MyUser;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{

    private UserService userService;
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    //signup
    @PostMapping("/signup")
    public ResponseEntity<MyUser> registerUser(@RequestBody MyUser myUser)
    {
        MyUser myUser1 = userService.registerUser(myUser);

        return ResponseEntity.ok(myUser1);
    }

    //login user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest)
    {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String username = authenticate.getName();

        return ResponseEntity.ok(username);
    }

    /*Controller calls → AuthenticationManager
    Manager calls → your CustomAuthenticationProvider
    Provider calls → CustomUserDetailsService
    Password match → success
    Spring sets authentication
    */
}
