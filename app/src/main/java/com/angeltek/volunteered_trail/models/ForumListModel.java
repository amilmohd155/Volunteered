package com.angeltek.volunteered_trail.models;

public class ForumListModel {

    private int profilePhoto;
    private String activityName;
    private String activeState;

    private String lastMessageText;
    private int lastMessageProfilePicture;
    private String lastMessageUsername;

    public ForumListModel(int profilePhoto, String activityName, String activeState, String lastMessageText, int lastMessageProfilePicture, String lastMessageUsername) {
        this.profilePhoto = profilePhoto;
        this.activityName = activityName;
        this.activeState = activeState;
        this.lastMessageText = lastMessageText;
        this.lastMessageProfilePicture = lastMessageProfilePicture;
        this.lastMessageUsername = lastMessageUsername;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActiveState() {
        return activeState;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public int getLastMessageProfilePicture() {
        return lastMessageProfilePicture;
    }

    public String getLastMessageUsername() {
        return lastMessageUsername;
    }
}
