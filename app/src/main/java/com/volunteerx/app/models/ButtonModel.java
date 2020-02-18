/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/10/20 9:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/10/20 9:48 PM
 *
 */

package com.volunteerx.app.models;

public class ButtonModel {

    String buttonText;
    int buttonDrawable;

    public ButtonModel(String buttonText) {
        this.buttonText = buttonText;
    }

    public ButtonModel(String buttonText, int buttonDrawable) {
        this.buttonText = buttonText;
        this.buttonDrawable = buttonDrawable;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public int getButtonDrawable() {
        return buttonDrawable;
    }

    public void setButtonDrawable(int buttonDrawable) {
        this.buttonDrawable = buttonDrawable;
    }
}
