package com.luoluohao.answer_system.x.as.admins.dto.entity;

import javax.persistence.*;

@Table(name = "admins")
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 管理员姓名
     */
    private String adminname;

    /**
     * 密码
     */
    private String password;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取管理员姓名
     *
     * @return adminname - 管理员姓名
     */
    public String getAdminname() {
        return adminname;
    }

    /**
     * 设置管理员姓名
     *
     * @param adminname 管理员姓名
     */
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}