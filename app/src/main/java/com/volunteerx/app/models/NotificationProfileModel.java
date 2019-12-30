/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.models;

public class NotificationProfileModel {

    private String notificationDescription;
    private String timestamp;
    private int profilePhotoPath;

    public NotificationProfileModel(String notificationDescription, String timestamp, int profilePhotoPath) {
        this.notificationDescription = notificationDescription;
        this.timestamp = timestamp;
        this.profilePhotoPath = profilePhotoPath;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(int profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }
}
