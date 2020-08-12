package com.zzxka.jhz.security.service;

import com.zzxka.jhz.system.entity.Menu;
import com.zzxka.jhz.system.entity.Role;
import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.service.MenuService;
import com.zzxka.jhz.system.service.RoleService;
import com.zzxka.jhz.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 自定义用户认定实现
 */
@Component
public class JhzUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.getUserByLoginName(username);
        if(null==user){
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Role> roleList=roleService.getRolesByUserId(user.getId());
        roleList.forEach(role -> {
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(role.getRoleKey());
            grantedAuthorities.add(grantedAuthority);
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getEnabled(),true,true,true,grantedAuthorities);
    }
}
