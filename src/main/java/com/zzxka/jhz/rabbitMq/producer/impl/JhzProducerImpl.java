package com.zzxka.jhz.rabbitMq.producer.impl;

import com.zzxka.jhz.rabbitMq.producer.JhzProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zzxka
 * @date: 2020-08-09
 * @description:
 */
@Component
public class JhzProducerImpl implements JhzProducer {
    private static final Logger log= LoggerFactory.getLogger(JhzProducerImpl.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String queue, Object message) {
        log.info("{}发送消息",queue);
        rabbitTemplate.convertAndSend(queue,message);
    }
}
