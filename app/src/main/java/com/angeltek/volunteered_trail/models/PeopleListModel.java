package com.angeltek.volunteered_trail.models;

public class PeopleListModel {

//    ("Fullname", "username", R.drawable.avatar0, false)

    private String fullName;
    private String username;
    private int profilePhoto;
    private boolean isFollowing;

    public PeopleListModel(String fullName, String username, int profilePhoto, boolean isFollowing) {
        this.fullName = fullName;
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.isFollowing = isFollowing;
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

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }
}
