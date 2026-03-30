package com.demo.entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserDetails implements UserDetails
{
    private MyUser user;

    public CustomUserDetails(MyUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> roles = user.getRoles();

        List<GrantedAuthority> authorities = new ArrayList<>();

       roles.stream()
               .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role)));


       Arrays.stream(user.getAuthorities().split(","))
               .map(SimpleGrantedAuthority::new)
               .forEach(authorities::add);

        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }



}
