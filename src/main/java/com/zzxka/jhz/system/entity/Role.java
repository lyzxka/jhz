package com.zzxka.jhz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
@TableName("sys_role")
public class Role {
    private Long role;
    private String roleName;
    private String roleKey;

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role=" + role +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                '}';
    }
}
