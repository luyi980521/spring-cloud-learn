package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by luyi on 2021/5/9 17:15.
 * 描述：网关服务启动类
 */
@SpringBootApplication
// 开启路由代理功能
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulFileApplication.class, args);
    }
}
