package com.zzxka.jhz.common;

/**
 * @author: zzxka
 * @date: 2020-08-09
 * @description:
 */
public class JhzException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;
    private int code = 500;

    public JhzException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public JhzException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public JhzException(int code,String msg ) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public JhzException(int code,String msg,  Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
