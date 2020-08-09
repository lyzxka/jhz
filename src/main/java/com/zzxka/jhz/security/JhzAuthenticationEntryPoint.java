package com.zzxka.jhz.security;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.zzxka.jhz.common.JhzResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 匿名用户访问无权限时的处理
 */
@Component
public class JhzAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("访问无权限");
        JhzResponse jhzResponse=JhzResponse.error(HttpStatus.HTTP_NOT_AUTHORITATIVE,"用户无此权限");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(jhzResponse));
    }
}
