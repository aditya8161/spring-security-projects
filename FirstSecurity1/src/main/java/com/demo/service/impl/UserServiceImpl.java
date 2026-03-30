package com.demo.service.impl;

import com.demo.entity.MyUser;
import com.demo.exception.UserNotFoundException;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MyUser registerUser(MyUser myUser) {
        Optional<MyUser> user = userRepository.findByUsername(myUser.getUsername());

        if(user.isPresent())throw new IllegalArgumentException("user already exist for thsi username : "+myUser.getUsername());

        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        MyUser savedUser = userRepository.save(myUser);
        return savedUser;
    }

    public MyUser getUserById(Long userId) {
        MyUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found : " + userId));
        return user;
    }

    @Transactional
    public MyUser updateUserById(Long userId,MyUser updatedUser) {
        MyUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found : " + userId));
        if(updatedUser.getAuthorities() != null){
            user.setAuthorities(user.getAuthorities());
        }

        return user;
    }

    @Override
    public void deleteUserById(Long userId) {
        MyUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found : " + userId));
        userRepository.deleteById(userId);
    }
}
