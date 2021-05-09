package com.lyman.controller;

import com.lyman.feign.UserRemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by luyi on 2021/5/7 22:37.
 * 描述：TODO
 */
@Slf4j
@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRemoteClient userRemoteClient;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/call/hello")
    // 设置fallback的方法和隔离策略
    @HystrixCommand(fallbackMethod = "defaultCallHello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
    })
    public String callHello() throws InterruptedException {
        log.info("进入callHello方法");
        String res = restTemplate.getForObject(
                "http://localhost:8081/houseData",
                String.class
        );
        log.info(res);
        return res;
    }

    @GetMapping(value = "/user/hello")
    public String userHello() {
        return userRemoteClient.hello();
    }

    public String defaultCallHello() {
        log.error("调用失败了，开始降级");
        return "fail";
    }
}
