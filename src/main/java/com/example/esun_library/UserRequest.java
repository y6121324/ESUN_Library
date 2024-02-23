package com.example.esun_library;

import java.util.Date;

public class UserRequest {
    private String phoneNumber;
    private String password;
    private String userName ;



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


}
