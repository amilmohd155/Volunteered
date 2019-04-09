package com.angeltek.volunteered_trail.models;

public class CustomSelectionModel {

    private String fullName, userName;
    private boolean selected = false;
    private int profilePhoto;

    public CustomSelectionModel(String fullName, String userName, int profilePhoto) {
        this.fullName = fullName;
        this.userName = userName;
        this.profilePhoto = profilePhoto;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    //Function to set Checked mark according to database data

}
