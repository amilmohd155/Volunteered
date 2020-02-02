/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 2:42 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 2:42 PM
 *
 */

package com.volunteerx.app.activity.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.activity.model.ReviewModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class ReviewBinder extends ItemBinder<ReviewModel, ReviewBinder.RViewHolder> {

    private RequestManager glide;

    public ReviewBinder(RequestManager glide) {
        this.glide = glide;
    }

    @Override
    public RViewHolder createViewHolder(ViewGroup parent) {
        return new RViewHolder(inflate(parent, R.layout.snippet_review_list_item));
    }

    @Override
    public void bindViewHolder(RViewHolder holder, ReviewModel item) {

        glide.load(item.getPhotoURl())
                .into(holder.civProfile);

        holder.tvName.setText(item.getReviewerName());
        holder.tvUsername.setText(item.getReviewerUsername());
        holder.tvRating.setText(holder.itemView.getContext().getString(R.string.rating_5, item.getRating()));
        holder.tvText.setText(item.getReviewText());

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof ReviewModel;
    }


    public class RViewHolder extends ItemViewHolder<ReviewModel> {

        CircleImageView civProfile;
        TextView tvName, tvUsername, tvRating, tvText;

        public RViewHolder(View itemView) {
            super(itemView);

            civProfile = itemView.findViewById(R.id.civ_profile_picture);
            tvName = itemView.findViewById(R.id.tv_reviewer_name);
            tvUsername = itemView.findViewById(R.id.tv_reviewer_username);
            tvRating = itemView.findViewById(R.id.tv_review_rating);
            tvText = itemView.findViewById(R.id.tv_review_text);
        }
    }
}
