package com.zzxka.jhz.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzxka.jhz.system.entity.Menu;
import com.zzxka.jhz.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description:
 */
@Component
public class JhzFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;
    /**
     * 验证请求地址是否具有权限
     * */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl=((FilterInvocation) object).getRequestUrl();
        // 获取当前用户权限
        UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<GrantedAuthority> grantedAuthorities=(List<GrantedAuthority>) userDetails.getAuthorities();
        String[] roles=new String[grantedAuthorities.size()];
        for(int i=0;i<grantedAuthorities.size();i++){
            GrantedAuthority authority=grantedAuthorities.get(i);
            roles[i]=authority.getAuthority();
        }
        List<Menu> menuList=menuService.getMenusBuRoleKeys(roles);
        for(Menu menu:menuList){
            if(StringUtils.equals(menu.getUrl(),requestUrl)){
                return SecurityConfig.createList(new String[]{requestUrl});
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
