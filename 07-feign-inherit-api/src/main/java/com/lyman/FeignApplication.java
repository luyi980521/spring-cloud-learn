package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by luyi on 2021/5/6 22:33.
 * 描述：TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(value = "com.lyman.feign")
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
