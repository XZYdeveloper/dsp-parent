package com.controller;

import com.entity.CenterResponse.CommentResponse;
import com.entity.CenterResponse.UserPageResponse;
import com.entity.GoodsComment;
import com.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author yangkun
 * @date 2020/2/3
 */
@RestController()
public class CenterController {


    @Autowired
    CenterService centerService;


    /**
     * 跳转到我的页面
     */
    @GetMapping("/toUserPage")
    public String toUserPage(HttpSession session){
        Long userId=(Long)session.getAttribute("userId");
        if(userId == null){
            return "请先登陆";
        }else{
            return null;
        }
    }
    /**
     * 实现订单评论---
     */
    @PostMapping("/comment/{goodsId}")
    public String addComment(GoodsComment goodsComment,
                                   HttpSession session,
                                   @PathVariable("goodsId") Long goodsId)

    {
        session.setAttribute("userId",2L);
        Long userId=(Long)session.getAttribute("userId");
        if(userId == null){
            return "评价失败，请先登陆";
        }else{
            goodsComment.setTimeCreate(new Date());
            goodsComment.setGoodsId(goodsId);
            goodsComment.setUserId(userId);
            centerService.getCenterMapper().insertComment(goodsComment);
            return "评价成功";
        }

    }


    /**
     * 跳转到商品所有评论---
     */
    @GetMapping("/getComments/{goodsId}")
    public List<CommentResponse> getGoodsdComments(@PathVariable("goodsId") Long goodsId){
        List<CommentResponse> comments=centerService.getGoodsComments(goodsId);
        return comments;
    }
    /**
     * 跳转到用户个人信息
     */
    @GetMapping("/userInfo")
    public String toUserCenter(HttpSession session){
        Long userId=(Long)session.getAttribute("userId");

        return null;
    }
    /**
     * 修改个人信息
     */
    @PostMapping("/updateInfo")
    public String updateUserInfo(HttpSession session){

        return "修改成功";
    }







}
