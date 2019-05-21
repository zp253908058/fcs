package com.zp.im.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected/user")
public class UserController {

    @GetMapping
    public String getUser(){
        return "zhang";
    }
}
