package com.lyman.hystrix;

import com.lyman.feign.UserRemoteClient;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * Created by luyi on 2021/5/8 12:43.
 * 描述：feign调用的fallback定义
 */
@Component
public class UserRemoteClientFallback implements UserRemoteClient {

    @Override
    public String hello() {
//        System.out.println(10 / 0);
        return "fail";
    }
}
