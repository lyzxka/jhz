package com.zzxka.jhz.system.controller;

import com.zzxka.jhz.rabbitMq.config.JhzRabbitQueue;
import com.zzxka.jhz.rabbitMq.producer.JhzProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zzxka
 * @date: 2020-07-20
 * @description:
 */
@RestController
public class BaseController {
    @Autowired
    private JhzProducer jhzProducer;

    @GetMapping("/testApi")
    public void test(HttpServletResponse response) throws Exception{
        /*jhzProducer.sendMessage(JhzRabbitQueue.JHZ_TEST_RABBIT_QUEUE,"1111111111");
        jhzProducer.sendMessage(JhzRabbitQueue.JHZ_TEST_RABBIT_QUEUE,"222222222");*/
        response.getWriter().println("asdasd");
    }
}
