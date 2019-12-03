package com.example.demo.enums;

public enum ResultCode {
    SUCCESS(1,"成功"),
    FAIL(0,"失败"),

    /**参数错误：1001-1999**/
    PARAM_IS_INVALID(1001,"参数无效"),
    PARAM_IS_BLANK(1002,"参数为空"),
    PARAM_TYPE_BAND_ERROR(1003,"参数类型错误"),
    PARAM_NOT_COMPLETE(1004,"参数缺失"),
    PARAM_LENGTH_IS_OVER(1005,"参数长度超出限制"),

    /**
     * 用户错误：2001-2999
     */
    USER_NOT_LOGGED_IN(2001,"用户未登录"),
    USER_LOGIN_ERROR(2002,"账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"账号被禁用"),
    USER_NOT_EXIST(2004,"用户未存在"),
    USER_HAS_EXISTED(2005,"用户已存在"),
    ;

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
