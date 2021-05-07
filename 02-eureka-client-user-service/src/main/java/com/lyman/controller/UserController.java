package com.lyman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luyi on 2021/5/5 17:02.
 * 描述：userController
 */
@RestController
public class UserController {

    @GetMapping("/user/hello")
    public String hello() {
        return "hello";
    }
}
