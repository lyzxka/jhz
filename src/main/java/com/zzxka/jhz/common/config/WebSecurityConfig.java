package com.zzxka.jhz.common.config;

import com.zzxka.jhz.security.*;
import com.zzxka.jhz.security.service.JhzUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Autowired
    private JhzFilterInvocationSecurityMetadataSource jhzFilterInvocationSecurityMetadataSource;
    @Autowired
    private JhzAbstractSecurityInterceptor jhzAbstractSecurityInterceptor;
    @Autowired
    private JhzAccessDecisionManager jhzAccessDecisionManager;
    @Autowired
    private JhzAuthenticationEntryPoint jhzAuthenticationEntryPoint;
    @Autowired
    private JhzAuthenticationFailureHandler jhzAuthenticationFailureHandler;
    @Autowired
    private JhzAuthenticationSuccessHandler jhzAuthenticationSuccessHandler;
    @Autowired
    private JhzLogoutSuccessHandler jhzLogoutSuccessHandler;
    @Autowired
    private JhzSessionInformationExpiredStrategy jhzSessionInformationExpiredStrategy;
    @Autowired
    private JhzUserDetailsService jhzUserDetailsService;

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
                        o.setAccessDecisionManager(jhzAccessDecisionManager);//访问决策管理器
                        o.setSecurityMetadataSource(jhzFilterInvocationSecurityMetadataSource);//安全元数据源
                        return o;
                    }
                });
        http.addFilterBefore(jhzAbstractSecurityInterceptor, FilterSecurityInterceptor.class);//增加到默认拦截链中
        // 异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(jhzAuthenticationEntryPoint); // 自定义访问未授权资源处理
        // 登录
        http.formLogin()
                .permitAll()//允许所有用户
                .successHandler(jhzAuthenticationSuccessHandler)// 自定义登录成功处理
                .failureHandler(jhzAuthenticationFailureHandler);// 自定义登录失败处理
        // 登出处理
        http.logout()
                .permitAll()// 允许所有用户
                .logoutSuccessHandler(jhzLogoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID");//登出之后删除cookie
        // 会话管理
        http.sessionManagement()
                .maximumSessions(1)
                .expiredSessionStrategy(jhzSessionInformationExpiredStrategy);
    }

    /**
     * 配置认证方式
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jhzUserDetailsService);
        auth.authenticationProvider(new JhzTokenAuthenticationProvider());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
}
