/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/12/20 6:58 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/12/20 6:58 PM
 *
 */

package com.volunteerx.app.profile.binder;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.profile.model.AccountModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class AccountBinder extends ItemBinder<AccountModel, AccountBinder.AccViewHolder> {

    private static final String TAG = "AccountBinder";

    private Context context;
    private RequestManager glide;

    public AccountBinder(Context context, RequestManager glide) {
        this.context = context;
        this.glide = glide;
    }

    @Override
    public AccViewHolder createViewHolder(ViewGroup parent) {
        return new AccViewHolder(inflate(parent, R.layout.snippet_account_selection_item));
    }

    @Override
    public void bindViewHolder(AccViewHolder holder, AccountModel item) {

        glide.load(item.getProfileUrl())
                .into(holder.civProfile);

        holder.tvUserName.setText(item.getUserName());

        Log.d(TAG, "bindViewHolder: holder.getAdapterPosition() :: " + holder.getAdapterPosition());

        if (holder.getAdapterPosition() == 0 || item.isActive()) {
            holder.tvUserName.setTypeface(Typeface.DEFAULT_BOLD);
            holder.ivCheck.setVisibility(View.VISIBLE);
        }
        else {
            holder.ivCheck.setVisibility(View.INVISIBLE);
            holder.tvUserName.setTypeface(Typeface.DEFAULT);
        }

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof AccountModel;
    }

    public class AccViewHolder extends ItemViewHolder<AccountModel> {

        TextView tvUserName;
        CircleImageView civProfile;
        ImageView ivCheck;

        public AccViewHolder(View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.username);
            ivCheck = itemView.findViewById(R.id.tick);
            civProfile = itemView.findViewById(R.id.avatar);

        }
    }
}
