package com.service;

import com.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sjj
 */
@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMap;

    public GoodsMapper getGoodsMap() {
        return goodsMap;
    }
}
