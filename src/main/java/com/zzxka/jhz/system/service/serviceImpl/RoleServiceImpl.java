package com.zzxka.jhz.system.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzxka.jhz.system.dao.RoleDao;
import com.zzxka.jhz.system.entity.Role;
import com.zzxka.jhz.system.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Override
    public List<Role> getRolesByUser(Long userId){
        return baseMapper.getRolesByUser(userId);
    }
}
