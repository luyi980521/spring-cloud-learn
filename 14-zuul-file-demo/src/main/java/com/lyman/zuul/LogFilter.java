package com.lyman.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by luyi on 2021/5/10 21:03.
 * 描述：日志记录
 */
@Slf4j
public class LogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
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
        HttpServletRequest req = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
        log.error("REQUEST: {} {}:{}", req.getScheme(), req.getRemoteAddr(), req.getRemotePort());
        StringBuilder sb = new StringBuilder("?");
        // 获取URL参数
        Enumeration<String> names = req.getParameterNames();
        if (req.getMethod().equals("GET")) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                sb.append(name).append("=")
                        .append(req.getParameter(name))
                        .append("&");
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        log.error("REQUEST: > {} {} {} {}", req.getMethod(), req.getRequestURI(), sb, req.getProtocol());
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            String value = req.getHeader(name);
            log.error("REQUEST: > {}:{}", name, value);
        }
        final RequestContext context = RequestContext.getCurrentContext();
        // 获取请求体参数
        if (!context.isChunkedRequestBody()) {
            ServletInputStream inp = null;
            try {
                inp = context.getRequest().getInputStream();
                String body = null;
                if (inp != null) {
                    body = IOUtils.toString(inp);
                    log.error("REQUEST: > {}", body);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
