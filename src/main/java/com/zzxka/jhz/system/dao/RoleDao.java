package com.zzxka.jhz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzxka.jhz.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {
    List<Role> getRolesByUserId(Long userId);
}
