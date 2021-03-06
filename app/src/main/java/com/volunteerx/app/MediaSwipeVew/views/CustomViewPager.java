/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 7:41 PM
 *
 */

package com.volunteerx.app.MediaSwipeVew.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private boolean mustWrapContent = true;
    private int height;

    public CustomViewPager(@NonNull Context context, boolean mustWrapContent) {
        super(context);
        this.mustWrapContent = mustWrapContent;
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs, boolean mustWrapContent) {
        super(context, attrs);
        this.mustWrapContent = mustWrapContent;
    }

    public CustomViewPager(@NonNull Context context, int height) {
        super(context);
        this.height = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        if (mustWrapContent) {
//
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

        if (mustWrapContent) {
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if (h > height) height = h;
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
