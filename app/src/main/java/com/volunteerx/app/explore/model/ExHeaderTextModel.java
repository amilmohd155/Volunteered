/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 11:19 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 11:19 AM
 *
 */

package com.volunteerx.app.explore.model;

public class ExHeaderTextModel {

    String mainHeading;
    String navHeading;

    public ExHeaderTextModel(String mainHeading, String navHeading) {
        this.mainHeading = mainHeading;
        this.navHeading = navHeading;
    }

    public String getMainHeading() {
        return mainHeading;
    }

    public void setMainHeading(String mainHeading) {
        this.mainHeading = mainHeading;
    }

    public String getNavHeading() {
        return navHeading;
    }

    public void setNavHeading(String navHeading) {
        this.navHeading = navHeading;
    }
}
