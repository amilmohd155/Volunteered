package com.volunteerx.app.models;

public class RequestProfileModel {

    private String fullName;
    private String username;
    private int profilePhotoId;
    private boolean ACCEPT;

    public RequestProfileModel(String fullName, String username, int profilePhotoPath) {

        this.fullName = fullName;
        this.username = username;
        this.profilePhotoId = profilePhotoPath;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public String getTimestamp() {
        return username;
    }

    public void setTimestamp(String timestamp) {
        this.username = timestamp;
    }

    public int getProfilePhotoPath() {
        return profilePhotoId;
    }

    public void setProfilePhotoPath(int profilePhotoPath) {
        this.profilePhotoId = profilePhotoPath;
    }

    public boolean isACCEPT() {
        return ACCEPT;
    }

    public void setACCEPT(boolean ACCEPT) {
        this.ACCEPT = ACCEPT;
    }

}
