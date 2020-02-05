package com.mapper;

import com.entity.Goods;
import com.entity.GoodsPic;

import java.util.List;

/**
 * @author sjj
 */
public interface GoodsMapper {
    //查询所有商品
    List<Goods> selectAllGoods();
    //通过id查询商品
    Goods selectGoodsById(Long goodsId);
    //通过商品名称模糊查询
    List<Goods> selectGoodsByTitle(String title);
    //通过id添加商品
    int  insertGoods(Goods goods);
    //通过id修改商品属性值
    int  updateGoodsById(Goods goods);
    //通过id删除商品
    int  delGoodsById(Long goodsId);
    //查询所有商品图片
    List<GoodsPic> queryAllPic();
    //查询图片通过商品id
    GoodsPic queryPicByGoodsId(Long goodsId);
    //添加图片通过id
    int  insertGoodsPic(GoodsPic pic);
    //通过id修改商品图片
    int  updatePicById(Long goodsId);
    //删除图片通过商品id
    int delGoodsPicById(Long goodsId);
}