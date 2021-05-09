package com.lyman.hystrix;

import com.lyman.feign.UserRemoteClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by luyi on 2021/5/9 09:19.
 * 描述：通过FallbackFactory实现回退功能，可查看出现回退的原因
 */
@Slf4j
@Component
public class UserRemoteClientFallbackFactory implements FallbackFactory<UserRemoteClient> {

    @Override
    public UserRemoteClient create(Throwable throwable) {
        log.error("UserRemoteClient回退 {}", throwable.getMessage());
        return new UserRemoteClient() {
            @Override
            public String hello() {
                return "失败了";
            }
        };
    }
}
