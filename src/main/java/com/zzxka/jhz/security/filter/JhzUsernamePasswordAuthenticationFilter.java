package com.zzxka.jhz.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-08-13
 * @description:
 */
public class JhzUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private AuthenticationManager authenticationManager;
    public JhzUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher("/auth/login", "POST"));
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("登录拦截器");
        JSONObject params=new JSONObject();
        try {
            params=new ObjectMapper().readValue(request.getInputStream(),JSONObject.class);
        } catch (IOException e) {
            System.out.println("获取参数异常");
        }
        System.out.println("登录参数："+params.toJSONString());
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(params.getString("username"),params.getString("password"));
        return authenticationManager.authenticate(token);
    }
}
