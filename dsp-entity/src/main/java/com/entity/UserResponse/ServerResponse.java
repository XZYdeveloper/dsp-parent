package com.entity.UserResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Auther: 王庆归
 * @Description:
 */

@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;   //状态
    private String msg;   //信息
    private T data;       //数据


    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    @JsonIgnore
    //使之不在json序列化结果当中即json里面不会出现这个
    public boolean isSuccess(){
        //如果status == 1 则返回true
        //SUCCESS的枚举值为1
        return this.status == ResponseCode.SUCCESS.getCode();
    }
    //返回状态
    public int getStatus(){
        return status;
    }
    //返回数据
    public T getData(){
        return data;
    }
    //返回信息
    public String getMsg(){
        return msg;
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

}

