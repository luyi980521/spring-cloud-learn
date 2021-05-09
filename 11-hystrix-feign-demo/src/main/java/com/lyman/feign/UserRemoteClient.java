package com.lyman.feign;

import com.lyman.hystrix.UserRemoteClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by luyi on 2021/5/8 12:41.
 * 描述：feign客户端
 */
@FeignClient(value = "user-service",
        fallbackFactory = UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {

    @GetMapping(value = "/user/hello")
    public String hello();
}
