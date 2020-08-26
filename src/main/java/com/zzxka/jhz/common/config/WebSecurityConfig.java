package com.zzxka.jhz.common.config;

import com.zzxka.jhz.security.*;
import com.zzxka.jhz.security.filter.JhzAbstractSecurityInterceptorFilter;
import com.zzxka.jhz.security.filter.JhzUsernamePasswordAuthenticationFilter;
import com.zzxka.jhz.security.handler.JhzAuthenticationFailureHandler;
import com.zzxka.jhz.security.handler.JhzAuthenticationSuccessHandler;
import com.zzxka.jhz.security.handler.JhzLogoutSuccessHandler;
import com.zzxka.jhz.security.provider.JhzAuthenticationProvider;
import com.zzxka.jhz.security.service.JhzUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    private JhzAuthenticationProvider jhzAuthenticationProvider;
    @Autowired
    private JhzAbstractSecurityInterceptorFilter jhzAbstractSecurityInterceptorFilter;
    @Autowired
    private JhzUserDetailsService jhzUserDetailsService;

    /**
     * http相关的配置，包括登入登出、异常处理、会话管理等
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(jhzAccessDecisionManager);//访问决策管理器
                        o.setSecurityMetadataSource(jhzFilterInvocationSecurityMetadataSource);//安全元数据源
                        return o;
                    }
                });
        http.addFilterAt(jhzAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jhzAbstractSecurityInterceptorFilter, FilterSecurityInterceptor.class);//增加到默认拦截链中
        // http.addFilter(new JhzBasicAuthenticationFilter(authenticationManager()));
        // 异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(jhzAuthenticationEntryPoint); // 自定义访问未授权资源处理
        // 登出处理
        http.logout()
                .permitAll()// 允许所有用户
                .logoutSuccessHandler(jhzLogoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID");//登出之后删除cookie
        // 使用token，不需要创建session，禁用掉session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 开启跨域
        http.cors();
        //取消跨域请求保护
        http.csrf().disable();
    }

    /**
     * 配置认证方式
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jhzUserDetailsService);
        auth.authenticationProvider(jhzAuthenticationProvider);
    }

    @Bean
    protected AuthenticationManager jhzAuthenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    JhzUsernamePasswordAuthenticationFilter jhzAuthenticationFilter() throws Exception{
        JhzUsernamePasswordAuthenticationFilter filter=new JhzUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(jhzAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(jhzAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/auth/login");
        filter.setAuthenticationManager(jhzAuthenticationManager());
        return filter;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
}
