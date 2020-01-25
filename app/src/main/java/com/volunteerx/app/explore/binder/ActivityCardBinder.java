/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 4:01 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 4:01 PM
 *
 */

package com.volunteerx.app.explore.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.explore.model.ActivityCardModel;
import com.volunteerx.app.utils.Converter;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

import static com.volunteerx.app.utils.Converter.NumberFormatting;

public class ActivityCardBinder extends ItemBinder<ActivityCardModel, ActivityCardBinder.CardViewHolder> {

    private RequestManager glide;
    private Context context;

    public ActivityCardBinder(Context context, RequestManager glide) {

        this.context = context;
        this.glide = glide;

    }

    @Override
    public CardViewHolder createViewHolder(ViewGroup parent) {
        return new CardViewHolder(inflate(parent,R.layout.layout_activity_card));
    }

    @Override
    public void bindViewHolder(CardViewHolder holder, ActivityCardModel item) {

        holder.tvActName.setText(item.getActName());
        holder.tvActCreator.setText(context.getString(R.string.by_creator, item.getActCreator()));
        holder.tvActFollower.setText(context.getString(R.string.followers_static, NumberFormatting(item.getFollowersCount())));

        holder.ratingBar.setRating(item.getRating());

        glide.load(item.getActImageUrl())
                .into(holder.ivActImage);

        holder.btnJoin.setOnClickListener(view -> {});

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof ActivityCardModel;
    }

    public class CardViewHolder extends ItemViewHolder<ActivityCardModel> {

        ImageView ivActImage, ivCharacterIcon;
        Button btnJoin;
        TextView tvActName, tvActCreator, tvActFollower;
        RatingBar ratingBar;

        public CardViewHolder(View itemView) {
            super(itemView);

            ivActImage = itemView.findViewById(R.id.iv_act_image);
            ivCharacterIcon = itemView.findViewById(R.id.iv_character_icon);
            btnJoin = itemView.findViewById(R.id.btn_join);
            tvActName = itemView.findViewById(R.id.tv_act_name);
            tvActFollower = itemView.findViewById(R.id.tv_act_followers);
            tvActCreator = itemView.findViewById(R.id.tv_act_creator);
            ratingBar = itemView.findViewById(R.id.act_rating);

        }
    }
}
