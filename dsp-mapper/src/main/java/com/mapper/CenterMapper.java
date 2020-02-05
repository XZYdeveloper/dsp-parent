package com.mapper;

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
    List<GoodsComment> selectAllCommentById(long goodsId);
    //3.拿到一条评论
    GoodsComment getComment(long commentId);
    //4.删除评论
    void deleteComment(long commentId);
    //5.修改评论--未完成
    void updateComment();
    //6.插入一条互评
    void insertCmother(GoodsCmother goodsCmother);
    //7.拿到某条评价的所有互评
    List<GoodsCmother> selectAllCmotherById(long commentId);
}
