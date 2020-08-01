package com.zzxka.jhz.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzxka.jhz.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
@Mapper
public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> getMenusBuRoles(Long[] roles);
    List<Menu> getMenusBuRoleKeys(String[] roles);
}
