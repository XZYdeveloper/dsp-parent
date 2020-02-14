package com.mapper;

import com.entity.SecKillResponse.KillGoods;
import com.entity.SecKillResponse.KillOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecKillMapper {
    List<KillGoods> getAllKillGoods();

    KillGoods getKillGoodsById(@Param("id") String id);

    KillOrder checkOrder(@Param("userid")String userId, @Param("goodsid")String goodsId);

    void doKill(@Param("userid")String userId, @Param("goodsid")String goodsId, @Param("time")String time);

    void decrStockById(@Param("id") String id);

    void resetStock(@Param("goodsid")String goodsId, @Param("stock")String stock);
}
