package com.zzxka.jhz.system.form;

import lombok.Data;

/**
 * @author: zzxka
 * @date: 2020-08-12
 * @description:
 */
public class Login {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
