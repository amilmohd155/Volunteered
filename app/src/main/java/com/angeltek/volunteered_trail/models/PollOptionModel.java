package com.angeltek.volunteered_trail.models;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PollOptionModel {

    private int circle;
    private String optionText;
    private int optionCount;

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }
}
