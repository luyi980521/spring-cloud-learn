package com.lyman.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by luyi on 2021/5/6 22:14.
 * 描述：自定义feign请求拦截器
 */
@Slf4j
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        log.info("请求已到达feignBasicAuthRequestInterceptor");
    }
}
