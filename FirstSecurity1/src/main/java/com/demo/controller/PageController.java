package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController
{
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/home")
    public String profilePage(){
        return "This is Home Page";
    }
}
