package com.lyman.controller;

import com.alibaba.fastjson.JSON;
import com.lyman.feign.UserRemoteClient;
import com.lyman.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by luyi on 2021/5/6 22:39.
 * 描述：TODO
 */
@Slf4j
@RestController
public class DemoController {

    @Autowired
    private UserRemoteClient userRemoteClient;

    @GetMapping("/call")
    public String callHello() {
        String res = userRemoteClient.getName();
        log.info("res: {}", res);
        return res;
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("name") String userName,
                              @RequestParam Integer age) {
        User user = userRemoteClient.getUserInfo(userName, age);
        String userJsonStr = JSON.toJSONString(user);
        log.info("用户信息: {}", userJsonStr);
        return userJsonStr;
    }

    @PostMapping("/addUser")
    public User addUser(User user) {
        String userJsonStr = userRemoteClient.addUser(user);
        log.info("user json format data: {}", userJsonStr);
        return JSON.parseObject(userJsonStr, User.class);
    }
}
