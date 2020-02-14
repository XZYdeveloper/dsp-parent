package com.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public final static String KILL_QUEUE = "kill_queue";

    @Bean
    public Queue queue() {
        return new Queue(MQConfig.KILL_QUEUE);
    }
}
