package com.zzxka.jhz.security;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * @author: zzxka
 * @date: 2020-07-30
 * @description: 会话信息过期策略
 */
public class JhzSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {

    }
}
