package com.zzxka.jhz.system.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzxka.jhz.system.dao.UserDao;
import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author: zzxka
 * @date: 2020-07-16
 * @description:
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
