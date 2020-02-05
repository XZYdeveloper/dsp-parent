package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangkun
 * @date 2020/2/3
 * 商品评价表
 */
public class GoodsComment implements Serializable {
    //评价表id
    private Long commentId;
    //用户id
    private Long userId;
    //商品id
    private Long goodsId;
    //评价内容
    private String content;
    //创建时间
    private Date timeCreate;
    //修改时间
    private Date timeModified;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }
}
