package com.service;

import com.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FangShuai on 20.2.3
 */

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
   /* @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private GoodsMapper goodsMapper;*/

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    /*public GoodsMapper getGoodsMapper() {
        return goodsMapper;
    }



    public ShopCarMapper getScUaMapper() {
        return shopCarMapper;
    }*/
}
