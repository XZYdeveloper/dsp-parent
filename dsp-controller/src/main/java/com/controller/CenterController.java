package com.controller;

import com.entity.GoodsCmother;
import com.entity.GoodsComment;

import com.mapper.CenterMapper;
import com.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yangkun
 * @date 2020/2/3
 */
@RestController
public class CenterController {


    @Autowired
    CenterService centerService;
    /**
     * 跳到我的主页
     */


    /**
     * 添加商品评论
     */

    @RequestMapping(value = "/addcomment",method = RequestMethod.POST)
    public String addCmother(GoodsComment goodsComment){
        centerService.getCenterMapper().insertComment(goodsComment);
        return "success";
    }
    /**
     * 获取某个商品的所有评论
     */

    @RequestMapping(value = "/getcomment/{id}",
                    method = RequestMethod.GET,
                    produces = {"application/json;charset=utf-8"})
    public List<GoodsComment> getAllBygoodsId(@PathVariable("id") long goodsId){
        return centerService.getCenterMapper().selectAllCommentById(goodsId);
    }
    /**
     * 用户删除自己的评价
     */
    @RequestMapping(value = "/deleteComment/userId={userId}&commentId={commentId}",method = RequestMethod.GET)
    public String deleteMyComment(@PathVariable("userId")long userId,@PathVariable("commentId")long commentId){
         GoodsComment goodsComment=centerService.getCenterMapper().getComment(commentId);
         if(goodsComment.getUserId()==userId) {
             centerService.getCenterMapper().deleteComment(commentId);
             return "删除成功";
         }else{
             return "删除失败";
         }
    }


}
