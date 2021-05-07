package com.lyman.client;

import com.lyman.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by luyi on 2021/5/6 21:30.
 * 描述：feign客户端
 */
// 指定调用的服务实例名称
@FeignClient(value = "user-service", configuration = FeignConfiguration.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    public String hello();

    @GetMapping("/house/data/{name}")
    public String getHouseData(@PathVariable("name") String name);
}
