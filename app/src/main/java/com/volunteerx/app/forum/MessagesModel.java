/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/14/20 9:59 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/14/20 9:59 PM
 *
 */


package com.volunteerx.app.forum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
