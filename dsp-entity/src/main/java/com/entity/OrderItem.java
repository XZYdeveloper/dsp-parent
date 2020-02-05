package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by FangShuai on 20.2.3
 */
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    /*订单项id*/
    private Long itemId;
    /*订单id*/
    private Long orderId;
    /*商品id*/
    private Integer goodsId;
    /*购买数量*/
    private Integer number;
    /*小计*/
    private Integer sum;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
