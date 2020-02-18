/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/12/20 6:59 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/12/20 6:59 PM
 *
 */

package com.volunteerx.app.profile.model;

public class AccountModel {

    private String profileUrl;
    private String userName;
    private boolean isActive;

    public AccountModel(String profileUrl, String userName, boolean isActive) {
        this.profileUrl = profileUrl;
        this.userName = userName;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
