/*
 * *
 *  * Created by Amil Muhammed Hamza on 3/1/20 11:01 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/1/20 11:01 PM
 *
 */

package com.volunteerx.app.startup.model;

public class SigningUser {

    String userData;
    String fullname;
    String password;

    public SigningUser(String userData, String fullname, String password) {
        this.userData = userData;
        this.fullname = fullname;
        this.password = password;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
