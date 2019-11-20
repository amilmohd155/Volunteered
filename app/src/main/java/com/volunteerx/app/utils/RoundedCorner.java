package com.volunteerx.app.utils;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

public class RoundedCorner {

    public static final int ALL_ROUNDED = 0;
    public static final int ROUNDED_TOP = 1;
    public static final int ROUNDED_BOTTOM = 2;
    public static final int ROUNDED_LEFT = 3;
    public static final int ROUNDED_RIGHT = 4;


//    TOdo finish the rounded corner problem
    public static void setRoundedCorner(View view, final int cornerRadius, final int type) {

        view.setClipToOutline(true);
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {

                switch (type) {
                    case ALL_ROUNDED:
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), cornerRadius);
                        break;
                    case ROUNDED_TOP:
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + cornerRadius, cornerRadius);
                        break;
                    case ROUNDED_BOTTOM:
                        break;
                    case ROUNDED_LEFT:
                        break;
                    case ROUNDED_RIGHT:
                        break;
                }
            }
        });

    }



}
