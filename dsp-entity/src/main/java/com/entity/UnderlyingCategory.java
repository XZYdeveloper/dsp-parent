package com.entity;

import java.io.Serializable;

/**
 * @author 杨可
 * 二级分类实体类
 */

public class UnderlyingCategory implements Serializable {

    private Integer id;
    private String title;
    private Integer cateId;

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

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
