package com.service;

import com.alibaba.fastjson.JSON;
import com.entity.SecKillResponse.Message;
import com.service.config.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecKillSender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(Message message) {
        String msg = JSON.toJSONString(message);
        rabbitTemplate.convertAndSend(MQConfig.KILL_QUEUE, msg);
    }

}
