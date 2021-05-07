package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by luyi on 2021/5/5 17:26.
 * 描述：eureka-server-cluster
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerClusterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerClusterApplication.class, args);
    }
}
