package com.zzxka.jhz.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxka.jhz.common.Constants.JhzConstants;
import com.zzxka.jhz.common.JhzResponse;
import com.zzxka.jhz.common.util.RedisUtils;
import com.zzxka.jhz.security.util.JwtTokenUtil;
import com.zzxka.jhz.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description:  登录成功处理
 */
@Component
public class JhzAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功");
        User user=(User)authentication.getPrincipal();
        String token= jwtTokenUtil.createAccessToken(user);
        String userTokenKey=JhzConstants.USER_LOGIN_STORAGE_TOKEN+user.getId();
        redisUtils.set(userTokenKey,token,JhzConstants.USER_LOGIN_STORAGE_EXPIRE);
        if(!authentication.getAuthorities().isEmpty()){
            List<Object> roles=new ArrayList<>();
            for(GrantedAuthority grantedAuthority:authentication.getAuthorities()){
                roles.add(grantedAuthority.getAuthority());
            }
            String userRoleKey=JhzConstants.USER_LOGIN_STORAGE_ROLE+user.getId();
            redisUtils.del(userRoleKey);
            redisUtils.lSet(userRoleKey,roles,JhzConstants.USER_LOGIN_STORAGE_EXPIRE);
        }
        response.setContentType("application/json;charset=utf-8");
        JhzResponse jhzResponse=JhzResponse.ok();
        jhzResponse.put("token",token).toString();
        response.getWriter().write(new ObjectMapper().writeValueAsString(jhzResponse));
    }
}
