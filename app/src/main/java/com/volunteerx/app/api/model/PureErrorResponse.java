/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/29/19 7:30 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/29/19 7:30 PM
 *
 */

package com.volunteerx.app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PureErrorResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;

    /**
     * No args constructor for use in serialization
     *
     */
    public PureErrorResponse() {
    }

    /**
     *
     * @param error
     */
    public PureErrorResponse(Boolean error) {
        super();
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

}
