/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/21/20 8:08 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/21/20 8:08 PM
 *
 */

package com.volunteerx.app.profile.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.VolunteerXDialog.VolunteerXDialog;
import com.volunteerx.app.profile.model.ProfilesModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class FollowBinder extends ItemBinder<ProfilesModel, FollowBinder.FollowViewHolder> {

    private RequestManager glide;
    private Context context;

    public FollowBinder(RequestManager glide, Context context) {

        this.glide = glide;
        this.context = context;
    }

    @Override
    public FollowViewHolder createViewHolder(ViewGroup parent) {
        return new FollowViewHolder(inflate(parent, R.layout.follow_list_item));
    }

    @Override
    public void bindViewHolder(FollowViewHolder holder, ProfilesModel item) {

        Button btnRemove = holder.btnRemove;
        ImageView ivMore = holder.ivMore;
        Button btnFollow = holder.btnFollow;

        glide.load(item.getProfilePhoto())
                .into(holder.civProfile);

        holder.tvName.setText(item.getName());
        holder.tvUsername.setText(item.getUsername());

        if (item.isFollower()) {

            btnRemove.setText(R.string.remove);
            ivMore.setVisibility(View.GONE);
            btnRemove.setVisibility(View.VISIBLE);
            btnFollow.setVisibility(View.GONE);

            btnRemove.setOnClickListener(v -> {
                // dialog for handling remove follower
                //TODO dialog stuffs
                Toast.makeText(context, "remove button clicked", Toast.LENGTH_SHORT).show();
            });


        }
        if (item.isFollowing()) {

            btnRemove.setText(R.string.following);
            ivMore.setVisibility(View.VISIBLE);
            btnFollow.setVisibility(View.GONE);
            btnRemove.setVisibility(View.VISIBLE);

            btnRemove.setOnClickListener(v -> {

                //dialog for handling not following
                Toast.makeText(context, "following button clicked", Toast.LENGTH_SHORT).show();


            });

            ivMore.setOnClickListener(vi -> {

                //notification and other handling
                Toast.makeText(context, "more option clicked", Toast.LENGTH_SHORT).show();

            });

        }
        if (item.isSuggested()){

            btnRemove.setVisibility(View.GONE);
            btnFollow.setText(R.string.follow);
            ivMore.setVisibility(View.VISIBLE);
            btnFollow.setVisibility(View.VISIBLE);

            btnFollow.setOnClickListener(v -> {

                //handle follow request
                Toast.makeText(context, "follow button clicked", Toast.LENGTH_SHORT).show();

            });

            ivMore.setOnClickListener(vi -> {

                //handle remove from suggestion kinda dialog
                Toast.makeText(context, "more option clicked", Toast.LENGTH_SHORT).show();

            });

        }



    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof ProfilesModel;
    }

    public class FollowViewHolder extends ItemViewHolder<ProfilesModel> {

        CircleImageView civProfile;
        TextView tvName, tvUsername;
        Button btnFollow, btnRemove;
        ImageView ivMore;

        public FollowViewHolder(View itemView) {
            super(itemView);

            civProfile = itemView.findViewById(R.id.profile_picture);
            tvName = itemView.findViewById(R.id.full_name);
            tvUsername = itemView.findViewById(R.id.user_name);
            btnFollow = itemView.findViewById(R.id.follow_btn);
            btnRemove = itemView.findViewById(R.id.remove_btn);
            ivMore = itemView.findViewById(R.id.ellipse_menu);

            itemView.setOnClickListener(v -> toggleItemSelection());

        }
    }
}
