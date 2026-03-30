package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityController
{
    @GetMapping("/read")
    public String readAuthority(){
        return "Read Authority";
    }

    @GetMapping("/write")
    public String writeAuthority(){
        return "Write Authority";
    }
}
