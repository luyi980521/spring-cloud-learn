package com.lyman.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * Created by luyi on 2021/5/5 18:12.
 * 描述：服务健康检查
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("status", false);
    }
}
