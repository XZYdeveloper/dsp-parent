package com.entity;

import java.util.Date;

public class Prefer_Comment {
    private int comment_id;
    private int user_id;
    private String content;
    private int prefer_id;
    private Date time_create;
    private Date time_modified;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime_modified() {
        return time_modified;
    }

    public void setTime_modified(Date time_modified) {
        this.time_modified = time_modified;
    }

    public Date getTime_create() {
        return time_create;
    }

    public void setTime_create(Date time_create) {
        this.time_create = time_create;
    }

    public int getPrefer_id() {
        return prefer_id;
    }

    public void setPrefer_id(int prefer_id) {
        this.prefer_id = prefer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }
}