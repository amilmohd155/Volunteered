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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.PeopleListModel;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class PeopleBinder extends ItemBinder<PeopleListModel, PeopleBinder.PeopleViewHolder> {

    private static final String TAG = "PeopleBinder";

    private Context context;

//    Constants
    private final int CUSTOM_SELECTION_TYPE = 0;
    private final int FOLLOWING_LIST = 1;
    private final int FOLLOWER_LIST = 2;

    public PeopleBinder(Context context) {
        this.context = context;
    }

    @Override
    public PeopleViewHolder createViewHolder(ViewGroup parent) {
        return new PeopleViewHolder(inflate(parent, R.layout.snippet_profile_selection_item));
    }

    @Override
    public void bindViewHolder(PeopleViewHolder holder, PeopleListModel item) {

        Glide.with(context)
                .load(item.getProfilePhoto())
                .into(holder.profilePicture);

        holder.TvFullName.setText(item.getFullName());
        holder.TvUserName.setText(item.getUsername());

        switch (item.getTypeOfLayout()) {
            case CUSTOM_SELECTION_TYPE:
                holder.followBtn.setVisibility(View.GONE);
                holder.ellipsesMenu.setVisibility(View.GONE);
                break;
            case FOLLOWING_LIST:
                holder.checkView.setVisibility(View.GONE);
                holder.ellipsesMenu.setVisibility(View.GONE);
                    holder.followBtn.setText(R.string.following);
                    holder.followBtn.setBackgroundColor(context.getColor(R.color.colorWhite));
                    holder.followBtn.setTextColor(context.getColor(R.color.colorBlack));
                break;
            case FOLLOWER_LIST:
                holder.checkView.setVisibility(View.GONE);
                if (item.isFollowing()) {
                    holder.followBtn.setText(R.string.following);
                    holder.followBtn.setBackgroundColor(context.getColor(R.color.colorWhite));
                    holder.followBtn.setTextColor(context.getColor(R.color.colorBlack));
                }
                holder.ellipsesMenu.setVisibility(View.VISIBLE); //click and slide up fragment comes to ask about un_following
                break;
                
        }

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof PeopleListModel;
    }

    public class PeopleViewHolder extends ItemViewHolder<PeopleListModel> {

        private CircleImageView profilePicture;
        private ImageView checkView, ellipsesMenu;
        private Button followBtn;
        private TextView TvFullName, TvUserName;

        public PeopleViewHolder(View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.civ_profile_picture);
            checkView = itemView.findViewById(R.id.check_view);
            ellipsesMenu = itemView.findViewById(R.id.ellipse_menu);
            followBtn = itemView.findViewById(R.id.follow_btn);
            TvFullName = itemView.findViewById(R.id.full_name);
            TvUserName = itemView.findViewById(R.id.user_name);

        }
    }
}
