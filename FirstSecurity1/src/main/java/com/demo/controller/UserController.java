package com.demo.controller;

import com.demo.entity.MyUser;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //register user
//    @PostMapping
//    public ResponseEntity<MyUser> registerUser(@RequestBody MyUser myUser)
//    {
//        MyUser myUser1 = userService.registerUser(myUser);
//
//        return ResponseEntity.ok(myUser1);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<MyUser> getUserById(@PathVariable Long userId)
    {
        MyUser myUser = userService.getUserById(userId);

        return ResponseEntity.ok(myUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId)
    {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<MyUser> updateUserById(@PathVariable Long userId,@RequestBody MyUser myUser){
        MyUser user =userService.updateUserById(userId,myUser);
        return ResponseEntity.ok(user);
    }
}
