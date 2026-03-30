package com.demo.service;

import com.demo.entity.MyUser;

public interface UserService
{
    //register user
    public MyUser registerUser(MyUser myUser);

    //login user

    //get user by id
    public MyUser getUserById(Long userId);

    //update user by id
    public MyUser updateUserById(Long userId,MyUser myUser);

    //delete user by id
    public void deleteUserById(Long userId);


}
