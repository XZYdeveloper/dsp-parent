package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangkun
 * @date 2020/2/3
 * 互评表
 */
public class GoodsCmother implements Serializable {
    //互评表id
    private Long cmotherId;
    //评论表id
    private Long commentId;
    //用户id
    private Long userId;
    //评价内容
    private String content;
    //评价时间
    private Date timeCreate;
    //评价修改时间
    private Date timeModified;

    public Long getCmotherId() {
        return cmotherId;
    }

    public void setCmotherId(Long cmotherId) {
        this.cmotherId = cmotherId;
    }

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
