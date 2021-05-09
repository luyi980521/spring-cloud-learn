package com.lyman.controller;

import com.lyman.emun.ResponseStatusEnum;
import com.lyman.res.RespResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by luyi on 2021/5/9 20:41.
 * 描述：处理过滤器中出现异常时，返回了非json格式数据的情况
 */
@RestController
public class ErrorHandlerController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * 异常页面处理方法
     *
     * @param req request对象
     * @return
     */
    @GetMapping("/error")
    public RespResult error(HttpServletRequest req) {
        Map<String, Object> errMap = getErrorAttributes(req);
        String message = (String) errMap.get("message");
        String trace = (String) errMap.get("trace");
        if (StringUtils.isNotBlank(trace)) {
            message += String.format(" and trace %s", trace);
        }
        return RespResult.fail(ResponseStatusEnum.SERVICE_ERROR.getCode(), message);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest req) {
        return errorAttributes.getErrorAttributes(new ServletWebRequest(req), true);
    }
}
