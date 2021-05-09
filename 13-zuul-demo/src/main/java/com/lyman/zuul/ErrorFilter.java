package com.lyman.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by luyi on 2021/5/9 20:30.
 * 描述：演示在filter中处理异常情况
 */
@Slf4j
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        Throwable throwable = context.getThrowable();
        log.error("异常信息：{}", throwable.getMessage());
        log.error("异常信息：{}", throwable.getCause().getMessage());
        return null;
    }
}
