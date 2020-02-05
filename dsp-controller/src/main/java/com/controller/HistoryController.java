package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.HistoryResponse.History;
import com.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistoryController {
    @Autowired
    HistoryService historyService;

    /**
     * 获取历史记录
     */
    @ResponseBody
    @RequestMapping(value = "/history/list",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String list(String userid) {
        History resp = historyService.getHistoryById(userid);
        return JSON.toJSONString(resp);
    }

    public void addHistory(String userid,String goodsid) {

    }

}
