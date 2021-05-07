package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by luyi on 2021/5/5 18:21.
 * 描述：TODO
 */
@EnableEurekaClient
@SpringBootApplication
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
