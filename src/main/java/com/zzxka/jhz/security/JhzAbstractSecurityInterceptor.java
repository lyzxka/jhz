package com.zzxka.jhz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 权限拦截器
 */
@Component
public class JhzAbstractSecurityInterceptor extends BasicAuthenticationFilter {

    @Autowired
    JhzFilterInvocationSecurityMetadataSource jhzFilterInvocationSecurityMetadataSource;

    public  JhzAbstractSecurityInterceptor(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("拦截器");
        SecurityContext context = SecurityContextHolder.getContext();
        /*if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
            System.out.println("被授权了");
            // do nothing
        } else {
            System.out.println("获取token参数");
            Map<String, String[]> params = request.getParameterMap();
            if (!params.isEmpty() && params.containsKey("token")) {
                String token = params.get("token")[0];
                if (token != null) {
                    Authentication auth = new JhzTokenAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }*/
        Map<String, String[]> params = request.getParameterMap();
        if (!params.isEmpty() && params.containsKey("token")) {
            String token = params.get("token")[0];
            if (token != null) {
                Authentication auth = new JhzTokenAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }


}
