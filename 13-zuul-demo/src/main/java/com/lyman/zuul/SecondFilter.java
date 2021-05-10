package com.lyman.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by luyi on 2021/5/9 20:14.
 * 描述：第二个filter，实现过滤器中的消息传递
 */
@Slf4j
public class SecondFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
//        int res = 10 / 0;
        log.info("ipFilter传递过来的默认数据: {}", ctx.get("default"));
        log.info("ipFilter传递过来的数据 msg: {}", ctx.get("msg"));
        return null;
    }
}
