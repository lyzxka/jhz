package com.zzxka.jhz.common.Constants;

/**
 * @author: zzxka
 * @date: 2020-08-14
 * @description:
 */
public class JhzConstants {
    /**
     * 用户登录role存储
     * */
    public static final String USER_LOGIN_STORAGE_ROLE="login:role:uid:";
    /**
     * 用户登录token存储
     * */
    public static final String USER_LOGIN_STORAGE_TOKEN="login:token:uid:";
    /**
     * 用户登录存储有效期  7天
     * */
    public static final long USER_LOGIN_STORAGE_EXPIRE=60*60*24*7;
    /**
     * 权限结合key前缀
     * */
    public static final String ROLE_LIST_KEY="role:";
}
