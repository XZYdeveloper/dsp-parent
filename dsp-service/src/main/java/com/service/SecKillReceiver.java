package com.service;

import com.alibaba.fastjson.JSON;
import com.entity.SecKillResponse.KillGoods;
import com.entity.SecKillResponse.KillOrder;
import com.entity.SecKillResponse.Message;
import com.service.config.MQConfig;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SecKillReceiver {
    private final static Logger logger = Logger.getLogger(SecKillReceiver.class);

    @Autowired
    private SecKillService service;

    @RabbitListener(queues = MQConfig.KILL_QUEUE)
    public void reveiceSKill(String message) {
        System.out.println("receive message" + message);
        Message msg = JSON.parseObject(message, Message.class);
        try {
            KillGoods goods = service.getStockById(msg.getGoodsId());
            if (goods.getStock() <= 0) {
                return;
            }
            //判断是否已经秒杀到了
            KillOrder order = service.checkIsKilled(msg.getUserId(), msg.getGoodsId());
            if (order != null) {
                return;
            }
            //减库存，下订单
            service.doKill(msg.getUserId(), msg.getGoodsId());
        } catch (Exception e) {
            logger.error("Receiver_Error \n" + e);
        }
    }
}
