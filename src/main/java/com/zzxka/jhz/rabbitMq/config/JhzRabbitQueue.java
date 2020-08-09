package com.zzxka.jhz.rabbitMq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zzxka
 * @date: 2020-08-09
 * @description:
 */
@Configuration
public class JhzRabbitQueue {

    // 测试队列
    public static final String JHZ_TEST_RABBIT_QUEUE="jhz_testRabbitQueue";

    @Bean
    public Queue testRabbitQueue(){
        return new Queue(JHZ_TEST_RABBIT_QUEUE);
    }
}
