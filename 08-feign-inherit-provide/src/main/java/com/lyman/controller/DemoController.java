package com.lyman.controller;

import com.alibaba.fastjson.JSON;
import com.lyman.feign.UserRemoteClient;
import com.lyman.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luyi on 2021/5/6 22:39.
 * 描述：TODO
 */
@RestController
public class DemoController implements UserRemoteClient {

    @Override
    public String getName() {
        return "lyman";
    }

    @Override
    public User getUserInfo(String name, Integer age) {
        return new User(1001, name, age);
    }

    @Override
    public String addUser(@RequestBody User user) {
        return JSON.toJSONString(user);
    }
}
