/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/10/20 3:03 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/10/20 3:03 AM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.models.ForumListModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class ForumListBinder extends ItemBinder<ForumListModel, ForumListBinder.ForumListViewHolder> {

    private Context context;
    private RequestManager glide;

    public ForumListBinder(Context context, RequestManager glide) {
        this.context = context;
        this.glide = glide;
    }

    @Override
    public ForumListViewHolder createViewHolder(ViewGroup parent) {
        return new ForumListViewHolder(inflate(parent, R.layout.layout_forum_list_item));
    }

    @Override
    public void bindViewHolder(ForumListViewHolder holder, ForumListModel item) {

        holder.tvActName.setText(item.getActivityName());
        holder.tvActFounder.setText(item.getFounder());
        holder.tvTimestamp.setText(item.getTimeStamp());

        holder.tvUnread.setText(context.getString(R.string.unread_messages, item.getUnReadMessages()));

        glide.load(item.getActImage())
                .override(80, 80)
                .dontAnimate()
                .into(holder.civActImage);
//        if (item.isState()) {
//            glide.load(R.drawable.forum_status_online)
//                    .into(holder.ivStatus);
//
//        }else {
//            glide.load(R.drawable.forum_status_offline)
//                    .into(holder.ivStatus);
//        }
//        if (item.isFavorite()) {
//            glide.load(R.drawable.ic_heart_filled_red)
//                    .into(holder.ivFav);
//        }else {
//            glide.load(R.drawable.ic_heart_outline)
//                    .into(holder.ivFav);
//        }
//
//        holder.ivFav.setOnClickListener(view -> {
//            if (item.isFavorite()) {
//                item.setFavorite(false);
//                glide.load(R.drawable.ic_heart_outline)
//                        .into(holder.ivFav);
//            }else {
//                item.setFavorite(true);
//                glide.load(R.drawable.ic_heart_filled_red)
//                        .into(holder.ivFav);
//            }
//        });

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof ForumListModel;
    }

    public class ForumListViewHolder extends ItemViewHolder<ForumListModel> {

        CircleImageView civActImage;
        ImageView ivStatus, ivFav;
        TextView tvActName, tvActFounder, tvUnread, tvTimestamp;

        public ForumListViewHolder(View itemView) {
            super(itemView);

            civActImage = itemView.findViewById(R.id.act_image);
            ivStatus = itemView.findViewById(R.id.status);
            ivFav = itemView.findViewById(R.id.forum_fav);
            tvActName = itemView.findViewById(R.id.act_name);
            tvActFounder = itemView.findViewById(R.id.act_founder);
            tvUnread = itemView.findViewById(R.id.unread_text);
            tvTimestamp = itemView.findViewById(R.id.update_timestamp);

            itemView.setOnClickListener(view -> toggleItemSelection());

        }
    }
}
