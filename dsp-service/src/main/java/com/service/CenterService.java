package com.service;

import com.entity.CenterResponse.CommentResponse;
import com.entity.GoodsCmother;
import com.entity.GoodsComment;
import com.mapper.CenterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkun
 * @date 2020/2/3
 */
@Service
public class CenterService {
    @Autowired
    CenterMapper centerMapper;

    public CenterMapper getCenterMapper() {
        return centerMapper;
    }

    public void setCenterMapper(CenterMapper centerMapper) {
        this.centerMapper = centerMapper;
    }
    /**
     * 拿到商品的评论的自定义类
     * */
    public List<CommentResponse>  getGoodsComments(Long goodsId){
        List<CommentResponse> comments = centerMapper.getGoodsComments(goodsId);
        return comments;
    }
    /**
     * 获取某个商品的所有评论---
     */
    public List<GoodsComment> getAllBygoodsId(Long goodsId){
        return centerMapper.selectAllCommentById(goodsId);
    }
}
