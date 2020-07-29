package com.zzxka.jhz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzxka.jhz.system.entity.User;
/**
 * @author: zzxka
 * @date: 2020-07-16
 * @description:
 */
public interface UserService extends IService<User> {
    org.springframework.security.core.userdetails.User getUser();
}
