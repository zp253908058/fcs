package com.zp.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: im
 * Created by Zhu Peng on 2019/5/22
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @PostMapping("/login")
    public Object login() {
        return "password";
    }

    public Object codeIn() {
        return "code";
    }
}
