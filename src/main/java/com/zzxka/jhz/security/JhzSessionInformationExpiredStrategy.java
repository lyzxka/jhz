package com.zzxka.jhz.security;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 会话信息过期策略
 */
@Component
public class JhzSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        System.out.println("会话管理");
    }
}
