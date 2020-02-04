package com.jingli.core.msg;

public class ErrorTip extends Tip {
    public ErrorTip(int code,String message){
        super.statusCode=code;
        super.msg=message;
    }

    public ErrorTip(String message){
        super.statusCode=500;
        super.msg=message;
    }

    public ErrorTip(){
        super.statusCode=500;
        super.msg="操作失败";
    }

}
