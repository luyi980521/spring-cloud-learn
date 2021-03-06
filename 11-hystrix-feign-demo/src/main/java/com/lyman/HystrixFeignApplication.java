package com.lyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by luyi on 2021/5/7 22:35.
 * 描述：TODO
 */
@SpringBootApplication
// 使用hystrix需要添加EnableHystrix或EnableCircuitBreaker注解（前者包含后者）
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.lyman.feign")
public class HystrixFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixFeignApplication.class, args);
    }
}
