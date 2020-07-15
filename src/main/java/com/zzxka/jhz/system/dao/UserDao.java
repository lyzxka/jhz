package com.zzxka.jhz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzxka.jhz.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zzxka
 * @date: 2020-07-16
 * @description:
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
