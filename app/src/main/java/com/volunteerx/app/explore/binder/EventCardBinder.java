/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 10:10 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 10:10 PM
 *
 */

package com.volunteerx.app.explore.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.explore.model.EventCardModel;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class EventCardBinder extends ItemBinder<EventCardModel, EventCardBinder.CardViewHolder> {

    private Context context;
    private RequestManager glide;

    public EventCardBinder(Context context, RequestManager glide) {
        this.context = context;
        this.glide = glide;
    }

    @Override
    public CardViewHolder createViewHolder(ViewGroup parent) {
        return new CardViewHolder(inflate(parent, R.layout.layout_event_card));
    }

    @Override
    public void bindViewHolder(CardViewHolder holder, EventCardModel item) {

    }

    @Override
    public boolean canBindData(Object item) {
        return false;
    }

    public class CardViewHolder extends ItemViewHolder<EventCardModel> {

        ImageView ivEventImage;

        public CardViewHolder(View itemView) {
            super(itemView);
        }
    }
}
