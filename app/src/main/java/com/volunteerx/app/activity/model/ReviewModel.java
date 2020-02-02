/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 2:44 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 2:44 PM
 *
 */

package com.volunteerx.app.activity.model;

public class ReviewModel {

    private int reviewID;
    private String photoURl;
    private String reviewerName;
    private String reviewerUsername;
    private float rating;
    private String reviewText;

    public ReviewModel(int reviewID, String photoURl, String reviewerName, String reviewerUsername, float rating, String reviewText) {
        this.reviewID = reviewID;
        this.photoURl = photoURl;
        this.reviewerName = reviewerName;
        this.reviewerUsername = reviewerUsername;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getPhotoURl() {
        return photoURl;
    }

    public void setPhotoURl(String photoURl) {
        this.photoURl = photoURl;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
