package com.lyman.controller;

import com.lyman.client.UserRemoteClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luyi on 2021/5/6 21:37.
 * 描述：helloController
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    private UserRemoteClient userRemoteClient;

    @GetMapping("/call/hello")
    public String hello() {
        String res = userRemoteClient.hello();
        log.info("调用hello方法的返回值: {}", res);
        return res;
    }

    @GetMapping("/call/houseData/{name}")
    public String acquireHouseData(@PathVariable String name) {
        String houseData = userRemoteClient.getHouseData(name);
        log.info("houseData: {}", houseData);
        return houseData;
    }
}
