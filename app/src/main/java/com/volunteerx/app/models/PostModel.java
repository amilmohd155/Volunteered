/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.models;

import com.volunteerx.app.MediaSwipeVew.media.MediaModel;

import java.util.ArrayList;
import java.util.List;

public class PostModel {

    private List<MediaModel> mediaUrl;
    private String profilePhotoUrl;
    private String fullName;
    private String textMessage;
    private String createdOn;
    private boolean isLiked;

    public PostModel(ArrayList<MediaModel> mediaUrl, String profilePhotoUrl, String fullName, String textMessage, String createdOn) {
        this.mediaUrl = mediaUrl;
        this.profilePhotoUrl = profilePhotoUrl;
        this.fullName = fullName;
        this.textMessage = textMessage;
        this.createdOn = createdOn;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public List<MediaModel> getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(List<MediaModel> mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
