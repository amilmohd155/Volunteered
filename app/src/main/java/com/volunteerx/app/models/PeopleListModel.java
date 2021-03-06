/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.models;

public class PeopleListModel {

//    ("Fullname", "username", R.drawable.avatar0, false)

    private String fullName;
    private String username;
    private String profilePhotoUrl;
    private boolean isFollowing;
    private int typeOfLayout;

    public PeopleListModel(String fullName, String username, String profilePhotoUrl, boolean isFollowing, int typeOfLayout) {
        this.fullName = fullName;
        this.username = username;
        this.profilePhotoUrl = profilePhotoUrl;
        this.isFollowing = isFollowing;
        this.typeOfLayout = typeOfLayout;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public int getTypeOfLayout() {
        return typeOfLayout;
    }

    public void setTypeOfLayout(int typeOfLayout) {
        this.typeOfLayout = typeOfLayout;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
