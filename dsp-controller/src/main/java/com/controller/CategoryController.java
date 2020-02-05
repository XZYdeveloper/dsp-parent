package com.controller;

import com.entity.TopCategory;
import com.entity.UnderlyingCategory;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 杨可
 */
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @ResponseBody
    @RequestMapping(value = "/underlycategory/{id}",method = RequestMethod.GET,produces ="application/json;charset=utf-8" )
    public List<UnderlyingCategory> findById(@PathVariable("id") Integer id)
    {
        List<UnderlyingCategory> list = categoryService.getMapper().findById(id);
        return list;
    }


    @ResponseBody
    @RequestMapping(value = "/topcategory",method = RequestMethod.GET,produces ="application/json;charset=utf-8" )
    public List<TopCategory> getAllTopCategory(){
        List<TopCategory> list = categoryService.getMapper().getAllTopCategory();
        return list;
    }
}
