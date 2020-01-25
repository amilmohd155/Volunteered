/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 4:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 4:02 PM
 *
 */

package com.volunteerx.app.explore.model;

public class ActivityCardModel {

    private String actName;
    private String actCreator;
    private String actImageUrl;

    private float rating;
    private int followersCount;

    private int characterId;

    public ActivityCardModel(String actName, String actCreator, String actImageUrl, float rating, int followersCount) {
        this.actName = actName;
        this.actCreator = actCreator;
        this.actImageUrl = actImageUrl;
        this.rating = rating;
        this.followersCount = followersCount;
//        this.characterId = characterId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActCreator() {
        return actCreator;
    }

    public void setActCreator(String actCreator) {
        this.actCreator = actCreator;
    }

    public String getActImageUrl() {
        return actImageUrl;
    }

    public void setActImageUrl(String actImageUrl) {
        this.actImageUrl = actImageUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }
}
