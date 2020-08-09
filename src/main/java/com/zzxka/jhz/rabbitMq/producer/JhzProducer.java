package com.zzxka.jhz.rabbitMq.producer;


/**
 * @author: zzxka
 * @date: 2020-08-09
 * @description:
 */
public interface JhzProducer {

    void sendMessage(String queue,Object message);
}
