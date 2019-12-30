/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/27/19 4:34 PM
 *
 */

package com.volunteerx.app.models;

import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;

public class User {

    private int userId;
    private String userName;
    private String fullName;
    private String emailAddress;
    private String phone;
    private String password;

    public User(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
    }

    public User(String username, String data, String password, int dataType) {
        if (dataType == EMAIL_ENTRY) {
            this.userName = username;
            this.emailAddress = data;
            this.password = password;
        }
        else if(dataType == PHONE_ENTRY) {
            this.userName = username;
            this.phone = data;
            this.password = password;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
