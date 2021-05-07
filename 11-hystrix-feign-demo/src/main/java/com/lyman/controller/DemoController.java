package com.lyman.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/call/hello")
    @HystrixCommand(fallbackMethod = "defaultCallHello")
    public String callHello() throws InterruptedException {
        log.info("进入callHello方法");
        String res = restTemplate.getForObject(
                "http://localhost:8081/houseData",
                String.class
        );
        log.info(res);
        return res;
    }

    public String defaultCallHello() {
        log.error("调用失败了，开始降级");
        return "fail";
    }
}
