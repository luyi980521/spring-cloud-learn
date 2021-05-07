package com.lyman.config;

import com.lyman.interceptor.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luyi on 2021/5/6 21:53.
 * 描述：feign的配置类
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "pwd");
    }

    @Bean
    public FeignBasicAuthRequestInterceptor feignBasicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
}
