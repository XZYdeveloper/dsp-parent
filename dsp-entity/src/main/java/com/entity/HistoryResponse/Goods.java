package com.entity.HistoryResponse;


import com.alibaba.fastjson.annotation.JSONType;

import java.math.BigDecimal;

/**
 * 响应数据中商品数据
 */
@JSONType(orders = {"id","title","price","pic","visitTime"})
public class Goods {
    private int id; //商品id
    private String title; //商品标题
    private BigDecimal price; //商品价格
    private String pic; //商品主图
    private String visitTime; //访问时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
