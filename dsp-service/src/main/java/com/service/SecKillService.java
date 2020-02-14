package com.service;

import com.entity.SecKillResponse.KillGoods;
import com.entity.SecKillResponse.KillOrder;
import com.mapper.SecKillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SecKillService {

    @Autowired
    private SecKillMapper mapper;

    public List<KillGoods> getAllKillGoods() {
        List<KillGoods> list = mapper.getAllKillGoods();
        return list;
    }

    public KillGoods getStockById(String id) {
        KillGoods goods = mapper.getKillGoodsById(id);
        return goods;
    }

    public KillOrder checkIsKilled(String userId, String goodsId) {
        KillOrder order = mapper.checkOrder(userId, goodsId);
        return order;
    }

    public void doKill(String userId, String goodsId) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        mapper.doKill(userId, goodsId, time);
        mapper.decrStockById(goodsId);
        return ;
    }

    public void resetStock(String goodId, String stock) {
        mapper.resetStock(goodId, stock);
    }

}
