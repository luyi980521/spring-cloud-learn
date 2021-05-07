package com.lyman.configuration;

import com.lyman.annotation.MyLoadBalanced;
import com.lyman.rule.MyRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by luyi on 2021/5/5 18:43.
 * 描述：bean的配置类
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @LoadBalanced
//    @MyLoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MyRule rule() {
        return new MyRule();
    }
}
