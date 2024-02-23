package com.example.esun_library;

import java.util.Date;

public class User {
    private Integer userId;
    private String phoneNumber;
    private String password;
    private String userName ;
    private Date registrationTime;
    private Date lastLoginTime;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }
}
