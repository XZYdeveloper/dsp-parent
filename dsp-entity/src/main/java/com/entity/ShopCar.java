package com.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 朱赵薇
 */
@Component
public class ShopCar implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long shopcarId; //'购物车表id',
    private Long userId; // '用户id',
    private Long goodsId; // '商品id',
    private int number; // '数量',

    public Long getShopcarId() {
        return shopcarId;
    }

    public void setShopcarId(Long shopcarId) {
        this.shopcarId = shopcarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "shopcarId=" + shopcarId +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", number=" + number +
                '}';
    }
}
