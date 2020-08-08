package com.zzxka.jhz.common;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * author: zzxka
 * date: 2020/8/8
 * description:
 */
public class JhzResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public JhzResponse(){
        put("code", HttpStatus.HTTP_OK);
        put("msg","success");
    }

    public static JhzResponse error(){
        return error(HttpStatus.HTTP_INTERNAL_ERROR,"服务器错误，请联系管理员");
    }
    public static JhzResponse error(String msg){
        return error(HttpStatus.HTTP_INTERNAL_ERROR,msg);
    }

    public static JhzResponse error(int code,String msg){
        JhzResponse response=new JhzResponse();
        response.put("code",code);
        response.put("msg",msg);
        return response;
    }

    public static JhzResponse ok(String msg){
        JhzResponse response=new JhzResponse();
        response.put("msg",msg);
        return response;
    }

    public static JhzResponse ok(){
        return new JhzResponse();
    }

    public static JhzResponse ok(Map<String,Object> data){
        JhzResponse response=new JhzResponse();
        response.putAll(data);
        return response;
    }

    public JhzResponse put(String key,String value){
        super.put(key,value);
        return this;
    }
}
