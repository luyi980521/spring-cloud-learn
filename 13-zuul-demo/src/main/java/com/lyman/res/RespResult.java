package com.lyman.res;

import com.lyman.emun.ResponseStatusEnum;

/**
 * Created by luyi on 2021/5/9 18:46.
 * 描述：返回结果对象
 */
public class RespResult {

    private Integer code;

    private String message;

    private Object data;

    public RespResult() {
        this.code = 200;
        this.message = "success";
        this.data = null;
    }

    public RespResult(Integer code, String message) {
        this(code, message, null);
    }

    public RespResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RespResult(ResponseStatusEnum resp) {
        this.code = resp.getCode();
        this.message = resp.getMessage();
        this.data = resp.getData();
    }

    public static RespResult ok() {
        return new RespResult();
    }

    public static RespResult ok(Object obj) {
        return new RespResult(200, "success", obj);
    }

    public static RespResult fail() {
        return new RespResult(500, "fail");
    }

    public static RespResult fail(ResponseStatusEnum resp) {
        return new RespResult(resp);
    }

    public static RespResult fail(Integer code, String message) {
        return new RespResult(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
