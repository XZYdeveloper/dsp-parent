package com.mapper;

import com.entity.CenterResponse.CommentResponse;
import com.entity.GoodsCmother;
import com.entity.GoodsComment;

import java.util.List;

/**
 * @author yangkun
 * @date 2020/2/3
 */
public interface CenterMapper {
    //1.插入一条商品评价
    void insertComment(GoodsComment goodsComment);
    //2.拿到某个商品的所有的评价
    List<GoodsComment> selectAllCommentById(Long goodsId);
    //3.拿到某个用户的所有评价
    List<GoodsComment> getComments(Long userId);
    //4.删除评论
    void deleteComment(Long commentId);
    //6.插入一条互评
    void insertCmother(GoodsCmother goodsCmother);
    //7.拿到某条评价的所有互评
    List<GoodsCmother> selectAllCmotherById(Long commentId);
    //9.评价页面自定义返回类
    List<CommentResponse> getGoodsComments(Long goodsId);

}
