/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/29/20 9:23 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/29/20 9:20 PM
 *
 */

package com.volunteerx.app.forum.room.binder;

import android.content.Context;
import android.net.Uri;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.github.piasy.biv.view.BigImageView;
import com.volunteerx.app.R;
import com.volunteerx.app.forum.room.model.Conversation;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class ForumChatBinder extends ItemBinder<Conversation, ForumChatBinder.ChatViewHolder> {

    private RequestManager glide;
    private Context context;

    public ForumChatBinder(RequestManager glide, Context context) {
        this.glide = glide;
        this.context = context;
    }

    @Override
    public ChatViewHolder createViewHolder(ViewGroup parent) {
        return new ChatViewHolder(inflate(parent, R.layout.layout_forum_message));
    }

    @Override
    public void bindViewHolder(ChatViewHolder holder, Conversation item, List payloads) {
        super.bindViewHolder(holder, item, payloads);

    }

    @Override
    public void bindViewHolder(ChatViewHolder holder, Conversation item) {

        holder.tvName.setText(item.getSender());
        holder.tvText.setText(item.getText());

        if (DateUtils.isToday(item.getCreatedAt())) {

            holder.tvTimestamp.setText(
                    context.getString(R.string.timestamp_today,
                            new SimpleDateFormat("HH:mm", Locale.getDefault())
                            .format(item.getCreatedAt())
                            )
            );

        }else {

            holder.tvTimestamp.setText(
                    new SimpleDateFormat("d MMM yyyy  HH:mm", Locale.getDefault())
                            .format(item.getCreatedAt())
            );

        }


        glide.load(item.getSenderProfile())
                .into(holder.ivProfile); //TOdo placeholder error
        if (item.getMedia() != null) {
            holder.ivMediaImage.showImage(Uri.parse(item.getMedia()), Uri.parse(item.getMedia())    );
        }
        else holder.ivMediaImage.setVisibility(View.GONE);



    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Conversation;
    }

    class ChatViewHolder extends ItemViewHolder<Conversation> {

        TextView tvName, tvTimestamp, tvText;
        CircleImageView ivProfile;
        BigImageView ivMediaImage;



        public ChatViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_sender_full_name);
            tvTimestamp = itemView.findViewById(R.id.tv_send_timestamp);
            tvText = itemView.findViewById(R.id.tv_sender_message);
            ivProfile = itemView.findViewById(R.id.iv_profile_picture);
            ivMediaImage = itemView.findViewById(R.id.iv_sender_image);
        }
    }
}
