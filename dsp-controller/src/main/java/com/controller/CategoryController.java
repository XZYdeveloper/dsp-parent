package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.Goods;
import com.entity.TopCategory;
import com.entity.UnderlyingCategory;
import com.github.pagehelper.PageHelper;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨可
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @ResponseBody
    @RequestMapping(value = "/findUnderlyCategoryById",method = RequestMethod.GET,produces ="application/json;charset=utf-8" )
    public String findById(@RequestParam("id")Integer id)
    {
        List<UnderlyingCategory> list = categoryService.getMapper().findUnderlyingById(id);
        return JSON.toJSONString(list);
    }


    @ResponseBody
    @RequestMapping(value = "/getAllTopCategory",method = RequestMethod.GET,produces ="application/json;charset=utf-8" )
    public String getAllTopCategory(){
        List<TopCategory> list = categoryService.getMapper().getAllTopCategory();
        return JSON.toJSONString(list);
    }


    @ResponseBody
    @RequestMapping(value = "/findGoodsById",method = RequestMethod.GET,produces ="application/json;charset=utf-8" )
    public String findGoodsById(@RequestParam("id")Integer id,@RequestParam("page")Integer page)
    {
        PageHelper.startPage(page,10);
        List<Goods> list = categoryService.getMapper().findGoodsById(id);
        return JSON.toJSONString(list);
    }
}
