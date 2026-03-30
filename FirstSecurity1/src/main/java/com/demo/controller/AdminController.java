package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    //only for admin work

    @GetMapping("/home")
    public String homePage(){
        return "Welcome Admin , This is home page";
    }
}
