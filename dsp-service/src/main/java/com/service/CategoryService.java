package com.service;

import com.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨可
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper mapper;

    public CategoryMapper getMapper(){
        return mapper;
    }

}
