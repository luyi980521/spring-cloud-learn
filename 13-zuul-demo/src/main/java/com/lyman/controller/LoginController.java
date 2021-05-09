package com.lyman.controller;

import com.lyman.pojo.User;
import com.lyman.res.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyi on 2021/5/9 17:47.
 * 描述：登录的controller
 */
@RestController
public class LoginController {

    @GetMapping("/local/{id}")
    public String local(@PathVariable String id) {
        return id;
    }

    @GetMapping("/queryUserInfo")
    public RespResult queryUserInfo() {
        List<User> res = new ArrayList<>();
        res.add(new User(1001, "jack", 20));
        res.add(new User(1002, "rose", 18));
        return RespResult.ok(res);
    }
}
