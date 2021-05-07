package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by luyi on 2021/5/6 22:40.
 * 描述：TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FeignConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumeApplication.class, args);
    }
}
