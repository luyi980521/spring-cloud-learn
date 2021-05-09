package com.lyman.zuul;

import com.alibaba.fastjson.JSON;
import com.lyman.enums.ResponseStatusEnum;
import com.lyman.res.RespResult;
import com.lyman.utils.IPUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyi on 2021/5/9 18:22.
 * 描述：ip过滤器，实现黑名单功能
 */
@Slf4j
public class IpFilter extends ZuulFilter {

    private List<String> blackIpList = new ArrayList<>();

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("IpFilter run");
        RequestContext context = RequestContext.getCurrentContext();
        context.set("default");
        context.set("msg", "你好呀！");
        String ip = IPUtil.getRequestIp(context.getRequest());
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
            log.info("{} 存在于ip黑名单中", ip);
            // 告诉zuul不需要将请求转发到后端服务
            context.setSendZuulResponse(false);
            RespResult res = new RespResult(ResponseStatusEnum.USER_ALREADY_IN_BLACK_LIST);
            context.setResponseBody(JSON.toJSONString(res));
            context.getResponse().setContentType("application/json; charset=utf-8");
        }
        return null;
    }
}
