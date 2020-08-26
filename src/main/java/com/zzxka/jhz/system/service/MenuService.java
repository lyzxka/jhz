package com.zzxka.jhz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzxka.jhz.system.entity.Menu;

import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
public interface MenuService extends IService<Menu> {

    List<Menu> getMenusByRoles(List<Long> roles);
    List<Menu> getMenusByRoleKeys(List<String> roles);
}
