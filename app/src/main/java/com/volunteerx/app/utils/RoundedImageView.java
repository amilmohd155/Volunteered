/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/11/20 9:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/11/20 9:05 PM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class RoundedImageView extends AppCompatImageView {

    private Context context;
    private int radius = 10;


    public RoundedImageView(Context context) {
        super(context);
        this.context = context;
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {

        GlideApp.with(context)
                .load(uri)
                .transform(new RoundedCorners(radius))
                .into(this);

    }

    @Override
    public void setImageResource(int resId) {

        GlideApp.with(context)
                .load(resId)
                .transform(new RoundedCorners(radius))
                .into(this);

    }
}
