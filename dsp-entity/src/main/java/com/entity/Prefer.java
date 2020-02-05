package com.entity;

import java.util.Date;

public class Prefer {
    private int Prefer_id;
    private int user_id;
    private int goods_id;
    private char pic;
    private char title;
    private String content;
    private int follow_number;
    private Date time_create;
    private Date time_modified;

    public char getTitle() {
        return title;
    }

    public void setTitle(char title) {
        this.title = title;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public char getPic() {
        return pic;
    }

    public void setPic(char pic) {
        this.pic = pic;
    }

    public Date getTime_create() {
        return time_create;
    }

    public void setTime_create(Date time_create) {
        this.time_create = time_create;
    }

    public Date getTime_modified() {
        return time_modified;
    }

    public void setTime_modified(Date time_modified) {
        this.time_modified = time_modified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFollow_number() {
        return follow_number;
    }

    public void setFollow_number(int follow_number) {
        this.follow_number = follow_number;
    }

    public int getPrefer_id() {
        return Prefer_id;
    }

    public void setPrefer_id(int prefer_id) {
        Prefer_id = prefer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}