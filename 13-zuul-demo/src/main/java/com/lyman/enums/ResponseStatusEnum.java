package com.lyman.enums;

/**
 * Created by luyi on 2021/5/9 18:48.
 * 描述：响应结果枚举对象
 */
public enum ResponseStatusEnum {

    USER_ALREADY_IN_BLACK_LIST(500, "该用户已经存在于黑名单中"),
    SERVICE_ERROR(500, "服务出现错误，请联系管理员"),
    ;

    private Integer code;

    private String message;

    private Object data;

    ResponseStatusEnum(Integer code, String message) {
        this(code, message, null);
    }

    ResponseStatusEnum(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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
