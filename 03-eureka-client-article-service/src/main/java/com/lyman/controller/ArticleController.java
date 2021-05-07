package com.lyman.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by luyi on 2021/5/5 17:07.
 * 描述：articleController
 */
@RestController
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/article/callHello")
    public String callHello() {
        return restTemplate.getForObject("http://user-service/user/hello", String.class);
    }

    @GetMapping("/article/infos")
    public List<InstanceInfo> infos() {
        return eurekaClient.getInstancesByVipAddress("user-service", false);
    }
}
