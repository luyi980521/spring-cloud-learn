package com.lyman.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by luyi on 2021/5/6 12:47.
 * 描述：TODO
 */
@RibbonClient(name = "ribbon-config-demo", configuration = BeanConfiguration.class)
public class RibbonClientConfig {
}
