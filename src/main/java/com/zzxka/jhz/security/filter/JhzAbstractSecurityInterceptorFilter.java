package com.zzxka.jhz.security.filter;

import com.zzxka.jhz.common.Constants.JhzConstants;
import com.zzxka.jhz.common.util.RedisUtils;
import com.zzxka.jhz.security.JhzAccessDecisionManager;
import com.zzxka.jhz.security.JhzFilterInvocationSecurityMetadataSource;
import com.zzxka.jhz.security.util.JwtTokenUtil;
import com.zzxka.jhz.system.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-08-14
 * @description:
 */
@Component
public class JhzAbstractSecurityInterceptorFilter extends AbstractSecurityInterceptor  implements Filter {
    @Autowired
    JhzFilterInvocationSecurityMetadataSource jhzFilterInvocationSecurityMetadataSource;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setMyAccessDecisionManager(JhzAccessDecisionManager jhzAccessDecisionManager) {
        super.setAccessDecisionManager(jhzAccessDecisionManager);
    }
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return jhzFilterInvocationSecurityMetadataSource;
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        System.out.println("拦截器");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token=httpServletRequest.getHeader("Authorization");
        if(!StringUtils.isBlank(token)) {
            System.out.println(token);
        }
        Claims claims = jwtTokenUtil.parserToken(token);
        List<Object> roles=redisUtils.lGet(JhzConstants.USER_LOGIN_STORAGE_ROLE+claims.getId(),0,-1);
        List<GrantedAuthority> authorities=new ArrayList<>();
        roles.forEach(role ->{
            authorities.add(new SimpleGrantedAuthority((String)role));
        });
        User user=new User();
        user.setUsername(claims.getSubject());
        user.setId(Long.parseLong(claims.getId()));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, claims.getId(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /*UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);*/
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }
}
