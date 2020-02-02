/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/29/20 9:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/16/20 9:38 PM
 *
 */

package com.volunteerx.app.forum.room.model;

public class Conversation {

    private String sender;
    private String senderProfile;
    private long createdAt;
    private String text;
    private String media;

    public Conversation() {
    }

    public Conversation(String sender, String senderProfile, long createdAt, String text, String media) {
        this.sender = sender;
        this.senderProfile = senderProfile;
        this.createdAt = createdAt;
        this.text = text;
        this.media = media;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderProfile() {
        return senderProfile;
    }

    public void setSenderProfile(String senderProfile) {
        this.senderProfile = senderProfile;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
