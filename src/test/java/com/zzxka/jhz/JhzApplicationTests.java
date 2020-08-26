package com.zzxka.jhz;

import com.zzxka.jhz.common.Constants.JhzConstants;
import com.zzxka.jhz.common.config.WebSecurityConfig;
import com.zzxka.jhz.common.util.RedisUtils;
import com.zzxka.jhz.rabbitMq.config.JhzRabbitQueue;
import com.zzxka.jhz.rabbitMq.producer.JhzProducer;
import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.service.MenuService;
import com.zzxka.jhz.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class JhzApplicationTests {
    private static final Logger log= LoggerFactory.getLogger(JhzApplicationTests.class);
    @Autowired
    UserService userService;
    @Autowired
    private JhzProducer jhzProducer;
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {
        User user=new User();
        user.setAddress("1111");
        user.setEmail("zzxka@sina.cn");
        user.setLoginName("admin");
        user.setUsername("Admin");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        userService.save(user);
        System.out.println(user.toString());
    }

    @Test
    public void testRabbit(){
        jhzProducer.sendMessage(JhzRabbitQueue.JHZ_TEST_RABBIT_QUEUE,"1111111111");
        jhzProducer.sendMessage(JhzRabbitQueue.JHZ_TEST_RABBIT_QUEUE,"222222222");
    }

    @Test
    public void redis(){
        redisUtils.set("11111","1");
        log.info(redisUtils.get("11111").toString());
        log.info(redisUtils.hasKey("11111")+"");
        redisUtils.del("11111");
        log.info(redisUtils.hasKey("11111")+"");
    }

    @Test
    public void menuTest(){
        List<String> list=new ArrayList<>();
        list.add("admin");
        menuService.getMenusByRoleKeys(list);
    }

    @Test
    public void addRoleCache(){
        List<String> roles=new ArrayList<>();
        roles.add("/testApi");
        redisUtils.lSet(JhzConstants.ROLE_LIST_KEY+"admin",roles);
    }
}
