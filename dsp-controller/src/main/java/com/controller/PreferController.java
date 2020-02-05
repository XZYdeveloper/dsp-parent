package com.controller;


import com.alibaba.fastjson.JSON;
import com.entity.Prefer;
import com.entity.Prefer_Cmother;
import com.entity.Prefer_Comment;
import com.service.PreferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PreferController {
    @Autowired
    PreferService service;

    @ResponseBody
    @RequestMapping(value="/prefer",method = RequestMethod.GET,produces =
            "application/json;charset=utf-8")
    public String prefer() {
        List<Prefer> list = service.getPrefer_mapper().getAll();
        return JSON.toJSONString(list);
    }
        @ResponseBody
        @RequestMapping(value="/prefer_cmother",method = RequestMethod.GET,produces =
                "application/json;charset=utf-8")
        public String prefer(){
            List<Prefer_Cmother> list1=service.getPrefer_cmother_mapper().getAll();
            return JSON.toJSONString(list1);
        }

    @ResponseBody
    @RequestMapping(value="/prefer_comment",method = RequestMethod.GET,produces =
            "application/json;charset=utf-8")
    public String prefer(){
        List<Prefer_Comment> list2=service.getPrefer_content_mapper().getAll();
        return JSON.toJSONString(list2);
    }

}
