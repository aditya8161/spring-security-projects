package com.demo.security;

import com.demo.entity.CustomUserDetails;
import com.demo.entity.MyUser;
import com.demo.exception.UserNotFoundException;
import com.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService
{
    //for load user by username in DB
    @Autowired
    private UserRepository userRepository;

    //cache create :- manually
    Map<String, MyUser> userMap = new ConcurrentHashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userMap.get(username);

        if(myUser != null){
            log.info("User Found in cache : {}",username);
            return new CustomUserDetails(myUser);
        }

        log.info("User not found in cache ,loading from DB");
        myUser=userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User not found in DB: "+username));

        userMap.put(username,myUser); //add new user in cache
        return new CustomUserDetails(myUser);
    }
}
