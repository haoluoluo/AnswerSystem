package com.luoluohao.answer_system.x.user.dto.entity;

import javax.persistence.*;

@Table(name = "user")
public class User {
    @Id
    private Integer userno;

    private String username;

    private String password;

    private String email;

    private String gender;

    private Integer status;

    private String newdate;

    /**
     * @return userno
     */
    public Integer getUserno() {
        return userno;
    }

    /**
     * @param userno
     */
    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return newdate
     */
    public String getNewdate() {
        return newdate;
    }

    /**
     * @param newdate
     */
    public void setNewdate(String newdate) {
        this.newdate = newdate;
    }
}