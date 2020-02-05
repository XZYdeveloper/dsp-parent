package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.Goods;
import com.entity.GoodsPic;
import com.service.GoodsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author sjj
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger logger = Logger.getLogger(GoodsController.class);
    @Autowired
    GoodsService gs;
    //查询所有商品
    @ResponseBody
    @RequestMapping(value = "/queryAllGoods",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String queryAllGoods(){
        List<Goods> goods = gs.getGoodsMap().selectAllGoods();

        return JSON.toJSONString(goods);
    }
    /*public String queryAllGoods(Model model){
        List<Goods> goods = gs.getGoodsMap().selectAllGoods();
        model.addAttribute("allGoodsList",goods);
        return "allGoodsList.html";
    }*/
    //查询商品通过id
    @ResponseBody
    @RequestMapping(value = "/queryGoodsById",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String queryGoodsById(Long id){
        Goods goods = gs.getGoodsMap().selectGoodsById(id);
        return JSON.toJSONString(goods);
    }
    //模糊查询商品通过title
    @ResponseBody
    @RequestMapping(value = "/queryGoodsByTitle",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String queryGoodsByTitle(String title){
        List<Goods> goods = gs.getGoodsMap().selectGoodsByTitle(title);
        return JSON.toJSONString(goods);
    }
    //通过id添加商品
    @ResponseBody
    @RequestMapping(value = "/insertGoods",method = RequestMethod.GET)
    public String insertGoods( Long goodsId, String title, BigDecimal price, Integer stock, Integer sales,
                               String content, Integer cateId, Date timeCreate){
        Goods goods=new Goods();
        goods.setGoodsId(goodsId);
        goods.setTitle(title);
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setSales(sales);
        goods.setContent(content);
        goods.setCateId(cateId);
        goods.setTimeCreate(timeCreate);
        int count=gs.getGoodsMap().insertGoods(goods);
        return "insert.html";
    }
    //通过id修改商品
    /*@ResponseBody
    @RequestMapping(value = "/updateGoodsById",method = RequestMethod.GET)
    public String updateGoods(Model mod,int goodsId, String title, Double price, int stock, int sales,
                              String content, int cateId, Date timeCreate){
        Goods goods=new Goods();
        goods.setGoodsId(goodsId);
        goods.setTitle(title);
        goods.setPrice(price);
        goods.setStock(stock);
        goods.setSales(sales);
        goods.setContent(content);
        goods.setCateId(cateId);
        goods.setTimeCreate(timeCreate);
        gs.getGoodsMap().updateGoodsById(goods);
        return "update.html";
    }*/
    //删除商品通过id
    @ResponseBody
    @RequestMapping(value = "/delGoodsById")
    public String delGoodsById(Long id){
        int count=gs.getGoodsMap().delGoodsById(id);
        return JSON.toJSONString(count);
    }
    //查询所有商品图片
    @ResponseBody
    @RequestMapping(value = "/selectAllGoodsPic",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String selectAllGoodsPic(){
        List<GoodsPic> goodsPics = gs.getGoodsMap().queryAllPic();
        return JSON.toJSONString(goodsPics);
    }
    //查询图片通过商品id
    @ResponseBody
    @RequestMapping(value = "/selectPicById",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String selectGoodsPicById(Long picId){
        GoodsPic goodsPics = gs.getGoodsMap().queryPicByGoodsId(picId);
        return JSON.toJSONString(goodsPics);
    }
    //添加图片通过id(有外键，无法添加)
    @ResponseBody
    @RequestMapping(value = "/insertPicById",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public String insertPicById(Long id,Long goodsId,String pic1,Integer level){
        GoodsPic pic = new GoodsPic();
        pic.setPicId(id);
        pic.setGoodsId(goodsId);
        pic.setPic(pic1);
        pic.setLevel(level);
        int count=gs.getGoodsMap().insertGoodsPic(pic);
        return "添加成功！";
    }
    //删除图片通过商品id
    @ResponseBody
    @RequestMapping(value="/delPicById")
    public String delGoodsPicById(Long picId){
        gs.getGoodsMap().delGoodsPicById(picId);
        return "删除图片成功！";
    }
}