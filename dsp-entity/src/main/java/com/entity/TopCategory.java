package com.entity;

import java.io.Serializable;

/**
 * @author 杨可
 * 一级分类实体类
 */

public class TopCategory implements Serializable {

    private Integer id;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
