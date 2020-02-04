package com.jingli.core.msg;

public class SuccessTip extends Tip{
    public SuccessTip(){
        super.statusCode=200;
        super.msg="操作成功";
    }
    public SuccessTip(Object obj){
        super.statusCode=200;
        super.msg=obj;
    }
}
