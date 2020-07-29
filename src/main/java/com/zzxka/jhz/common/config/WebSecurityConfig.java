package com.zzxka.jhz.common.config;

import com.zzxka.jhz.security.*;
import com.zzxka.jhz.security.service.JhzUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zzxka
 * @date: 2020-07-20
 * @description:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * http相关的配置，包括登入登出、异常处理、会话管理等
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(new JhzAccessDecisionManager());//访问决策管理器
                        o.setSecurityMetadataSource(new JhzFilterInvocationSecurityMetadataSource());//安全元数据源
                        return o;
                    }
                });
        http.addFilterBefore(new JhzAbstractSecurityInterceptor(), FilterSecurityInterceptor.class);//增加到默认拦截链中
        // 异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(new JhzAuthenticationEntryPoint()); // 自定义访问未授权资源处理
        // 登录
        http.formLogin()
                .permitAll()//允许所有用户
                .successHandler(new JhzAuthenticationSuccessHandler())// 自定义登录成功处理
                .failureHandler(new JhzAuthenticationFailureHandler());// 自定义登录失败处理
        // 登出处理
        http.logout()
                .permitAll()// 允许所有用户
                .logoutSuccessHandler(new JhzLogoutSuccessHandler())//登出成功处理逻辑
                .deleteCookies("JSESSIONID");//登出之后删除cookie
        // 会话管理
        http.sessionManagement()
                .maximumSessions(1)
                .expiredSessionStrategy(new JhzSessionInformationExpiredStrategy());
    }

    /**
     * 配置认证方式
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(new JhzUserDetailsService());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
}
