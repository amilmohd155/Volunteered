/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/29/19 7:26 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/28/19 5:12 PM
 *
 */


package com.volunteerx.app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error-value")
    @Expose
    private Integer errorValue;
    @SerializedName("user-id")
    @Expose
    private Integer userID;

    /**
     * No args constructor for use in serialization
     *
     */
    public Response() {
    }

    /**
     *
     * @param errorValue
     * @param error
     * @param message
     * @param userID
     */
    public Response(Boolean error, String message, Integer errorValue, Integer userID) {
        super();
        this.error = error;
        this.message = message;
        this.errorValue = errorValue;
        this.userID = userID;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(Integer errorValue) {
        this.errorValue = errorValue;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}