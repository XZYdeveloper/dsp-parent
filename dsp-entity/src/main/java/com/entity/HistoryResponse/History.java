package com.entity.HistoryResponse;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.List;

/**
 * 返回数据
 */
@JSONType(orders = {"status","userId","list"})
public class History {
    private int status; //状态码1响应正确，0响应失败
    private int userId; //用户id
    List<Goods> list; //商品数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}
