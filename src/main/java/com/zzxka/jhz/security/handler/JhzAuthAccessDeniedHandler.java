package com.zzxka.jhz.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.zzxka.jhz.common.JhzResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-08-11
 * @description: 暂无权限处理
 */
public class JhzAuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("用户未授权");
        JhzResponse jhzResponse=JhzResponse.error(HttpStatus.HTTP_FORBIDDEN,"用户未授权");
        response.getWriter().write(JSON.toJSONString(jhzResponse));
    }
}
