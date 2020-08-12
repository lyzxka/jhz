package com.zzxka.jhz.security.handler;

import com.zzxka.jhz.common.JhzResponse;
import com.zzxka.jhz.security.util.JwtTokenUtil;
import com.zzxka.jhz.system.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description:  登录成功处理
 */
@Component
public class JhzAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("访问有权限");
        User user=(User)authentication.getPrincipal();
        String token= JwtTokenUtil.createAccessToken(user);
        response.getWriter().write(JhzResponse.ok().put("token",token).toString());
    }
}
