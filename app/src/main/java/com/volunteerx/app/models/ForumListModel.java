/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.models;

public class ForumListModel {

    private int forumId;

    private String founder;
    private String activityName;
    private String timeStamp;

    private String  actImage;
    private int popularityIndex;
    private int unReadMessages;

    private boolean isOwning; //true==Self-Owned
    private boolean isClosed;
    private boolean isFavorite; //true==Liked
    private boolean state; //true==online


    public ForumListModel(int forumId, String founder, String activityName, String timeStamp, String actImage, int unReadMessages, boolean isFavorite, boolean state) {
        this.forumId = forumId;
        this.founder = founder;
        this.activityName = activityName;
        this.timeStamp = timeStamp;
        this.actImage = actImage;
        this.unReadMessages = unReadMessages;
        this.isFavorite = isFavorite;
        this.state = state;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getActImage() {
        return actImage;
    }

    public void setActImage(String  actImage) {
        this.actImage = actImage;
    }

    public int getPopularityIndex() {
        return popularityIndex;
    }

    public void setPopularityIndex(int popularityIndex) {
        this.popularityIndex = popularityIndex;
    }

    public int getUnReadMessages() {
        return unReadMessages;
    }

    public void setUnReadMessages(int unReadMessages) {
        this.unReadMessages = unReadMessages;
    }

    public boolean isOwning() {
        return isOwning;
    }

    public void setOwning(boolean owning) {
        isOwning = owning;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
}
