package com.zzxka.jhz.system.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzxka.jhz.system.dao.MenuDao;
import com.zzxka.jhz.system.entity.Menu;
import com.zzxka.jhz.system.service.MenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Override
    public List<Menu> getMenusByRoles(Long[] roles) {
        return baseMapper.getMenusBuRoles(roles);
    }

    @Override
    public List<Menu> getMenusBuRoleKeys(String[] roles) {
        return baseMapper.getMenusBuRoleKeys(roles);
    }
}
