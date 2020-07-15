package com.zzxka.jhz;

import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JhzApplicationTests {
    private static final Logger log= LoggerFactory.getLogger(JhzApplicationTests.class);
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user=new User();
        user.setAddress("1111");
        user.setEmail("zzxka@sina.cn");
        user.setLoginName("admin");
        user.setUsername("Admin");
        userService.save(user);
        System.out.println(user.toString());
    }

}
