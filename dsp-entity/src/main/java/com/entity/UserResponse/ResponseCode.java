package com.entity.UserResponse;

/**
 * @Auther: 王庆归
 * @Description:
 */
public enum ResponseCode {
    SUCCESS(1,"SUCCESS"),
    ERROR(0,"ERROR");


    //成员变量（常量）
    private final int code;
    private final String desc;

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
    //构造方法
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static void main(String args[]){
        System.out.println(ResponseCode.SUCCESS.getDesc());
        System.out.println(ResponseCode.SUCCESS.getCode());
    }
}