/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/29/20 9:28 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/29/20 9:20 PM
 *
 */


package com.volunteerx.app.forum.room.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volunteerx.app.forum.room.model.Conversation;

import java.util.List;

public class MessagesModel {

    @SerializedName("participants")
    @Expose
    private List<String> participants = null;
    @SerializedName("conversation")
    @Expose
    private List<Conversation> conversation = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MessagesModel() {
    }

    /**
     *
     * @param conversation the conversation class
     * @param participants the member list
     */
    public MessagesModel(List<String> participants, List<Conversation> conversation) {
        super();
        this.participants = participants;
        this.conversation = conversation;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<Conversation> getConversation() {
        return conversation;
    }

    public void setConversation(List<Conversation> conversation) {
        this.conversation = conversation;
    }

}
