package com.lyman.configuration;

import com.lyman.annotation.MyLoadBalanced;
import com.lyman.intercepter.MyLoadBalancerInterceptor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luyi on 2021/5/5 20:16.
 * 描述：自定义负载均衡自动配置类
 */
@Configuration
public class MyLoadBalancerAutoConfiguration {

    @MyLoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplateList =
            Collections.emptyList();

    @Bean
    public MyLoadBalancerInterceptor myLoadBalancerInterceptor() {
        return new MyLoadBalancerInterceptor();
    }

    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer() {
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate :
                        MyLoadBalancerAutoConfiguration.this.restTemplateList) {
                    List<ClientHttpRequestInterceptor> list =
                            new ArrayList<>(restTemplate.getInterceptors());
                    list.add(myLoadBalancerInterceptor());
                    restTemplate.setInterceptors(list);
                }
            }
        };
    }
}
