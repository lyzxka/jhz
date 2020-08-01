package com.zzxka.jhz.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: zzxka
 * @date: 2020-08-01
 * @description:
 */
@TableName("sys_menu")
public class Menu {
    private Long id;
    private String name;
    private String url;
    private String permission;
    private String icon;
    private Long parentId;
    private Integer level;
    private Integer type;
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", type=" + type +
                ", sort=" + sort +
                '}';
    }
}
