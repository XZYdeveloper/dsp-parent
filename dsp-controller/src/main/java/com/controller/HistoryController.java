package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.HistoryResponse.History;
import com.entity.HistoryResponse.UploadResponse;
import com.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HistoryController {
    @Autowired
    HistoryService historyService;

    /**
     * 如果在history下获取其他的api则返回404
     */
    @ResponseBody
    @RequestMapping(value = {"/history","/history/*"}, produces = "application/json;charset=utf-8")
    public String info404() {
        History resp = historyService.info404();
        return JSON.toJSONString(resp);
    }

    /**
     * 获取历史记录
     */
    @ResponseBody
    @RequestMapping(value = "/history/list",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String list(HttpServletRequest req) {
        String id = getParameterValue(req,"userid");
        History resp = historyService.getHistoryById(id);
        return JSON.toJSONString(resp);
    }

    /**
     * 根据userid和goodsid添加记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/history/add", method = RequestMethod.POST ,produces = "application/json;charset=utf-8")
    public String addHistory(HttpServletRequest req) {
        String userid = getParameterValue(req,"userid");
        String goodsid = getParameterValue(req,"goodsid");
        History resp = historyService.addHistory(userid,goodsid);
        return JSON.toJSONString(resp);
    }

    /**
     * 从HttpServletRequest中获取参数
     * @param param
     * @return
     */
    private String getParameterValue(HttpServletRequest req,String param) {
        return null!=req.getParameter(param)?req.getParameter(param):"";
    }

    /**
     * 文件上传到七牛云服务器
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String upload(@RequestParam("file") MultipartFile file) {
        UploadResponse resp = new UploadResponse();
        //如果文件为空
        if(file.isEmpty()) {
            resp.setStatus(0);
            return JSON.toJSONString(resp);
        }
        String ret = historyService.upload(file);
        if("error".equals(ret)) {
            resp.setStatus(0);
        } else {
            resp.setStatus(1);
            resp.setUrl(ret);
        }
        return JSON.toJSONString(resp);
    }

}
