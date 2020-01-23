/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/21/20 8:09 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/21/20 8:09 PM
 *
 */

package com.volunteerx.app.profile.model;

public class ProfilesModel {

    private String name;
    private String username;
    private String profilePhoto;
    private boolean isFollowing;
    private boolean isFollower;
    private boolean isSuggested;

    public ProfilesModel(String fullName, String username, String profilePhoto, boolean isFollowing, boolean isFollower, boolean isSuggested) {
        this.name = fullName;
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.isFollowing = isFollowing;
        this.isFollower = isFollower;
        this.isSuggested = isSuggested;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public boolean isFollower() {
        return isFollower;
    }

    public void setFollower(boolean follower) {
        isFollower = follower;
    }

    public boolean isSuggested() {
        return isSuggested;
    }

    public void setSuggested(boolean suggested) {
        isSuggested = suggested;
    }
}
