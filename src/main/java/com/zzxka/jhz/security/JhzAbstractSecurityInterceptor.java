package com.zzxka.jhz.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 权限拦截器
 */
public class JhzAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
