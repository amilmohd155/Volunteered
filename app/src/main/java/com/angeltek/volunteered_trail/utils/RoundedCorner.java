package com.angeltek.volunteered_trail.utils;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

public class RoundedCorner {

    private View view;
    private int curveRadius;
    private int type = 0;
    public static final int ALL_ROUNDED = 0;
    public static final int ROUNDED_TOP = 1;
    public static final int ROUNDED_BOTTOM = 2;
    public static final int ROUNDED_LEFT = 3;
    public static final int ROUNDED_RIGHT = 4;

    public RoundedCorner(View view, int cornerRadius, int type) {
        this.view = view;
        this.curveRadius = cornerRadius;
        this.type = type;
    }


//    TOdo finish the rounded corner problem
    public void setRoundedCorner() {

        view.setClipToOutline(true);
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                switch (type) {
                    case ALL_ROUNDED:
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), curveRadius);
                        break;
                    case ROUNDED_TOP:
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + curveRadius, curveRadius);
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
