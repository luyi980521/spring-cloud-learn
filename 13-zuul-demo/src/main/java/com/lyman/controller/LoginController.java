package com.lyman.controller;

import com.lyman.pojo.User;
import com.lyman.res.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyi on 2021/5/9 17:47.
 * 描述：登录的controller
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

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

    @GetMapping("/call/hello")
    public String hello() {
        String res = restTemplate.getForObject(
                "http://user-service/house/data?name=lyman",
                String.class
        );
        log.info(res);
        return res;
    }
}
