/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:14 PM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.volunteerx.app.R;
import com.volunteerx.app.models.ScreenItem;


import java.util.List;

public class OnBoardingViewPagerAdapter extends PagerAdapter {

    private Context context;
    List<ScreenItem> screenItems;

    public OnBoardingViewPagerAdapter(Context context, List<ScreenItem> screenItems) {
        this.context = context;
        this.screenItems = screenItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_on_boarding_screen, null);

        final ImageView imgSlide = view.findViewById(R.id.iv_info);
        final TextView tvTitle = view.findViewById(R.id.tv_title);
        final TextView tvDescription = view.findViewById(R.id.tv_description);

        tvTitle.setText(screenItems.get(position).getTitle());
        tvDescription.setText(screenItems.get(position).getDescription());
        Glide.with(context)
                .load(screenItems.get(position).getScreenImg())
                .into(imgSlide);

        container.addView(view);

        return view;

    }

    @Override
    public int getCount() {
        return screenItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }



}
