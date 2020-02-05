package com.entity;

import java.io.Serializable;

/**
 * @author 杨可
 * 二级分类实体类
 */

public class UnderlyingCategory implements Serializable {

    private Integer id;
    private String title;
    private Integer cate1_id;
    private Integer level;

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

    public Integer getCate1_id() {
        return cate1_id;
    }

    public void setCate1_id(Integer cate1_id) {
        this.cate1_id = cate1_id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
