package com.entity;

import java.util.Date;

public class Prefer_Cmother {
    private int cmother_id;
    private int user_id;
    private int comment_id;
    private String content;
    private Date time_create;
    private Date time_modified;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCmother_id() {
        return cmother_id;
    }

    public void setCmother_id(int cmother_id) {
        this.cmother_id = cmother_id;
    }
}