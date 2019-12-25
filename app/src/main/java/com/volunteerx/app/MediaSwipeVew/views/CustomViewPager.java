package com.volunteerx.app.MediaSwipeVew.views;

import android.content.Context;
import android.util.AttributeSet;

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
        if (mustWrapContent) {

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
