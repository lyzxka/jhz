package com.zzxka.jhz.system.controller;

import com.zzxka.jhz.common.JhzResponse;
import com.zzxka.jhz.security.util.JwtTokenUtil;
import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.form.Login;
import com.zzxka.jhz.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zzxka
 * @date: 2020-08-12
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/auth/")
public class LoginController {
    private static final Logger log= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public JhzResponse login(@RequestBody Login form){
        log.info("用户登录：{}",form.toString());
        return JhzResponse.ok();
    }
}
