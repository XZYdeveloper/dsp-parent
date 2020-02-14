package com.entity.SecKillResponse;

/**
 * RabbitMQ发送的消息类
 */
public class Message {

    private String userId;
    private String goodsId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
