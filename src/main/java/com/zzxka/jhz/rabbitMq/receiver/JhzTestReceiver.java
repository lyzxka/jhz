package com.zzxka.jhz.rabbitMq.receiver;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zzxka.jhz.rabbitMq.config.JhzRabbitQueue;
import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zzxka
 * @date: 2020-08-09
 * @description:
 */
@Component
public class JhzTestReceiver {
    private static final Logger log= LoggerFactory.getLogger(JhzTestReceiver.class);
    @Autowired
    private UserService userService;

    @RabbitListener(queues = JhzRabbitQueue.JHZ_TEST_RABBIT_QUEUE)
    public void testReceiveMsg(Object message){
        log.info("收到来自{}的消息：{}",JhzRabbitQueue.JHZ_TEST_RABBIT_QUEUE,message.toString());
        User userOld=userService.getById(1283438489848438785L);
        if(userOld.getEnabled().equals(false)){
            log.info("已经被修改了");
            return ;
        }
        User user=new User();
        user.setEnabled(false);
        boolean update=userService.update(user,new LambdaUpdateWrapper<User>().eq(User::getId,1283438489848438785L));
        if(update){
            log.info("用户信息修改了:{}",message.toString());
        }else{
            log.info("用户信息未修改:{}",message.toString());
        }
    }
}
