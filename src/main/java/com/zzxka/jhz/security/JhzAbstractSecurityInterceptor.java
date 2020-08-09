package com.zzxka.jhz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 权限拦截器
 */
@Component
public class JhzAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    JhzFilterInvocationSecurityMetadataSource jhzFilterInvocationSecurityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Autowired
    public void setAccessDecisionManager(JhzAccessDecisionManager jhzAccessDecisionManager) {
        super.setAccessDecisionManager(jhzAccessDecisionManager);
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return jhzFilterInvocationSecurityMetadataSource;
    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        System.out.println(filterInvocation);
        InterceptorStatusToken statusToken = super.beforeInvocation(filterInvocation);
        try {
            // 执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(statusToken, null);
            SecurityContextHolder.clearContext();
        }
    }

}
