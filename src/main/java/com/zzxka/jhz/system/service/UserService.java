package com.zzxka.jhz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzxka.jhz.system.entity.User;

import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-07-16
 * @description:
 */
public interface UserService extends IService<User> {
    User getUserByLoginName(String loginName);
}
