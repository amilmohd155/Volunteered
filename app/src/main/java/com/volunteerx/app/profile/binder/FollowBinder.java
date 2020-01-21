/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/21/20 8:08 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/21/20 8:08 PM
 *
 */

package com.volunteerx.app.profile.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.volunteerx.app.R;
import com.volunteerx.app.profile.model.ProfilesModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

import static com.volunteerx.app.utils.Constants.FOLLOWERS_LIST;
import static com.volunteerx.app.utils.Constants.FOLLOWING_LIST;

public class FollowBinder extends ItemBinder<ProfilesModel, FollowBinder.FollowViewHolder> {

    private RequestManager glide;
    private int viewType;

    public FollowBinder(RequestManager glide, int viewType) {

        this.glide = glide;
        this.viewType = viewType;

    }

    @Override
    public FollowViewHolder createViewHolder(ViewGroup parent) {
        return new FollowViewHolder(inflate(parent, R.layout.follow_list_item));
    }

    @Override
    public void bindViewHolder(FollowViewHolder holder, ProfilesModel item) {

        glide.load(item.getProfilePhoto())
                .into(holder.civProfile);

        switch (viewType) {
            case FOLLOWING_LIST:

                break;
            case FOLLOWERS_LIST:

                break;
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

        }
    }
}
