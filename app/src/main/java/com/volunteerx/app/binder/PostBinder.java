/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.MediaSwipeVew.views.MediaSwipeView;
import com.volunteerx.app.R;
import com.volunteerx.app.models.PostModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

import static com.volunteerx.app.utils.Converter.pTd;

public class PostBinder extends ItemBinder<PostModel, PostBinder.PostViewHolder> {

    private RequestManager glide;
    private Context context;

    public PostBinder(Context context, RequestManager glide) {

        this.context = context;
        this.glide = glide;

    }


    @Override
    public PostViewHolder createViewHolder(ViewGroup parent) {
        return new PostViewHolder(inflate(parent, R.layout.layout_post));
    }

    @Override
    public void bindViewHolder(PostBinder.PostViewHolder holder, PostModel item) {

        glide.load(item.getProfilePhotoUrl())
                .into(holder.civProfilePicture);

        if (item.getMediaUrl() != null)
            holder.mediaSwipeView.setMedia(item.getMediaUrl());
        else
            holder.mediaSwipeView.setVisibility(View.GONE);

        holder.tvFullName.setText(item.getFullName());

        holder.tvTimeStamp.setText(item.getCreatedOn());

        holder.tvText.setText(item.getTextMessage());

        //Todo complete

    }


    @Override
    public boolean canBindData(Object item) {
        return item instanceof PostModel;
    }

    public class PostViewHolder extends ItemViewHolder<PostModel>{

        TextView tvFullName, tvTimeStamp, tvText;
        MediaSwipeView mediaSwipeView;
        CircleImageView civProfilePicture;
        ImageView ivMore, ivLike, ivComment, ivShare;

        public PostViewHolder(View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.tv_full_name);
            tvTimeStamp = itemView.findViewById(R.id.tv_timestamp);
            tvText = itemView.findViewById(R.id.postDescription);
            mediaSwipeView = itemView.findViewById(R.id.postMedia);
            civProfilePicture = itemView.findViewById(R.id.profilePhoto);
            ivMore = itemView.findViewById(R.id.post_ellipses);
            ivLike = itemView.findViewById(R.id.postLikeBtn);
            ivComment = itemView.findViewById(R.id.postCommentBtn);
            ivShare = itemView.findViewById(R.id.postShareBtn);

        }
    }
}
