package com.zzxka.jhz.security.provider;

import cn.hutool.http.HttpStatus;
import com.zzxka.jhz.common.JhzException;
import com.zzxka.jhz.system.entity.Role;
import com.zzxka.jhz.system.entity.User;
import com.zzxka.jhz.system.service.RoleService;
import com.zzxka.jhz.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-09
 * @description:
 */
@Component
public class JhzAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("进行校验token");
        // 获取表单输入中返回的用户名
        String userName = authentication.getName();
        // 获取表单中输入的密码
        String password = authentication.getCredentials().toString();
        System.out.println(userName+"  "+password);
        User user=userService.getUserByLoginName(userName);
        if(null==user){
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Role> roleList=roleService.getRolesByUserId(user.getId());
        roleList.forEach(role -> {
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(role.getRoleKey());
            grantedAuthorities.add(grantedAuthority);
        });

        // 返回新的认证信息，带上 token 和反查出的用户信息
        Authentication auth = new PreAuthenticatedAuthenticationToken(user, password, grantedAuthorities);
        auth.setAuthenticated(true);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
