/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/27/20 5:21 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/25/20 8:22 PM
 *
 */

package com.volunteerx.app.explore.model;


import static com.volunteerx.app.utils.Constants.NUM_CHAR_POST_CARD;

public class PostCardModel {

    private String profilePicture;
    private String name;
    private String textPost;
    private String mediaUrl;
    private int[] characterList;

    private int likeCount;
    private int commentCount;

    private boolean isFollowing;
    private boolean isLiked;

    public PostCardModel(String profilePicture, String name,
                         String textPost, String mediaUrl,
                         int likeCount, int commentCount,
                         boolean isFollowing, boolean isLiked,
                         int[] characterList ) {

        this.profilePicture = profilePicture;
        this.name = name;
        this.textPost = textPost;
        this.mediaUrl = mediaUrl;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.isFollowing = isFollowing;
        this.isLiked = isLiked;
        this.characterList = characterList;

    }

    public int getRemainingCharacter() {
        return characterList.length - NUM_CHAR_POST_CARD;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int[] getCharacterList() {
        return characterList;
    }

    public void setCharacterList(int[] characterList) {
        this.characterList = characterList;
    }
}
