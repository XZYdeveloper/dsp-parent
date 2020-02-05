package com.service;

import com.mapper.ShopCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author ÷Ï’‘ﬁ±
 */
@Service
public class ShopCarService {
    @Autowired
    private ShopCarMapper shopcarMapper;
    @Autowired
    //private UserMapper userMapper;

    public ShopCarMapper getShopcarMapper() {
        return shopcarMapper;
    }
    /*public UserMapper getUserMapper() {
        return userMapper;
    }

     */
}
