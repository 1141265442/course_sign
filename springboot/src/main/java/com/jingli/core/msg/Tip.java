package com.jingli.core.msg;

public abstract class Tip {
    Integer statusCode;
    Object msg;

    public Integer getCode() {
        return statusCode;
    }

    public void setCode(Integer code) {
        this.statusCode = code;
    }

    public Object getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
