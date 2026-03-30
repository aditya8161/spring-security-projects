package com.demo.test1;

import com.demo.entity.MyUser;
import com.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class UserTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test1(){
        System.out.println("Hello");
    }

    //add user
    @Test
    public void addUser(){
        System.out.println("1");
        Set<String> roles=new HashSet<>();
        roles.add("USER");

        MyUser myUser =new MyUser();
        myUser.setUsername("aditya123");
        myUser.setPassword("pass123");
        myUser.setRoles(roles);
        myUser.setAuthorities("READ");

        System.out.println("2");
        MyUser savedUser = userRepository.save(myUser);

        System.out.println(myUser);
    }
}
