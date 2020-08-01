package com.zzxka.jhz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzxka.jhz.system.entity.Role;

import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
public interface RoleService extends IService<Role> {
    List<Role> getRolesByUser(Long userId);
}
