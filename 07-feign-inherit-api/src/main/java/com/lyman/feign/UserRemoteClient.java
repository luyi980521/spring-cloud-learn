package com.lyman.feign;

import com.lyman.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luyi on 2021/5/6 22:34.
 * 描述：feign-inherit-provide服务feign客户端
 */
@FeignClient(value = "feign-inherit-provide")
public interface UserRemoteClient {

    @GetMapping("/user/name")
    public String getName();

    @GetMapping("/getUserInfo")
    public User getUserInfo(@RequestParam String name,
                            @RequestParam Integer age);

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user);
}
