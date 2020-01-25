/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 10:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 10:11 PM
 *
 */

package com.volunteerx.app.explore.model;

public class EventCardModel {

    private int eventId;
    private int eventFollowers;

    private float eventRating;

    private String eventName;
    private String eventLocation;
    private String eventDuration;
    private String eventImageURL;

    public EventCardModel(int eventId, int eventFollowers, float eventRating, String eventName, String eventLocation, String eventDuration, String eventImageURL) {
        this.eventId = eventId;
        this.eventFollowers = eventFollowers;
        this.eventRating = eventRating;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDuration = eventDuration;
        this.eventImageURL = eventImageURL;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventFollowers() {
        return eventFollowers;
    }

    public void setEventFollowers(int eventFollowers) {
        this.eventFollowers = eventFollowers;
    }

    public float getEventRating() {
        return eventRating;
    }

    public void setEventRating(float eventRating) {
        this.eventRating = eventRating;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(String eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventImageURL() {
        return eventImageURL;
    }

    public void setEventImageURL(String eventImageURL) {
        this.eventImageURL = eventImageURL;
    }
}
