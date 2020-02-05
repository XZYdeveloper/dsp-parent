package com.entity;

import java.io.Serializable;
import java.util.*;

/**
 * @author 杨可
 * 一级分类实体类
 */

public class TopCategory implements Serializable {

    private Integer id;
    private String title;
    private Integer level;
    private List<UnderlyingCategory> underlyingCategories;

    public List<UnderlyingCategory> getUnderlyingCategories() {
        return underlyingCategories;
    }

    public void setUnderlyingCategories(List<UnderlyingCategory> underlyingCategories) {
        this.underlyingCategories = underlyingCategories;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
