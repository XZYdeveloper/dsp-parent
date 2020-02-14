package com.entity.SecKillResponse;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"status", "result"})
/**
 * status 1秒杀到  0派对中  -1没有秒杀到
 */
public class Response {
    private int status;
    private String result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
