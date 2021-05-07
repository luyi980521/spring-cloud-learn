package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by luyi on 2021/5/6 21:26.
 * 描述：TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.lyman.client")
public class FshSubstitutionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FshSubstitutionServiceApplication.class, args);
    }
}
