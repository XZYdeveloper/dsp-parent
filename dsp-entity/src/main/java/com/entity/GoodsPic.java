package com.entity;

/**
 * @author sjj
 */

public class GoodsPic {
    private Long picId;    //商品图片id
    private Long goodsId;   //商品id
    private String pic;    //商品图片
    private Integer level;    //图片编号

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
