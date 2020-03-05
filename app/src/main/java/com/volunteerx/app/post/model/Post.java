/*
 * *
 *  * Created by Amil Muhammed Hamza on 3/1/20 11:53 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/1/20 11:53 AM
 *
 */

package com.volunteerx.app.post.model;

import com.google.firebase.firestore.ServerTimestamp;
import com.volunteerx.app.MediaSwipeVew.media.MediaModel;

import java.util.Date;
import java.util.List;

public class Post {

    @ServerTimestamp
    Date timestamp;

    String postID;
    String postedByID;
    String postedByName;
    String profileUrl;
    String postText;

    List<MediaModel> mediaURLArray;

    public Post(Date timestamp, String postedByID, String postedByName, String profileUrl, String postText, List<MediaModel> mediaURLArray) {
        this.timestamp = timestamp;
        this.postedByID = postedByID;
        this.postedByName = postedByName;
        this.profileUrl = profileUrl;
        this.postText = postText;
        this.mediaURLArray = mediaURLArray;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostedByID() {
        return postedByID;
    }

    public void setPostedByID(String postedByID) {
        this.postedByID = postedByID;
    }

    public String getPostedByName() {
        return postedByName;
    }

    public void setPostedByName(String postedByName) {
        this.postedByName = postedByName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public List<MediaModel> getMediaURLArray() {
        return mediaURLArray;
    }

    public void setMediaURLArray(List<MediaModel> mediaURLArray) {
        this.mediaURLArray = mediaURLArray;
    }
}
