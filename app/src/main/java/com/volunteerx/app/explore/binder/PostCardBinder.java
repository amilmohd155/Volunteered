/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 11:34 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 11:34 AM
 *
 */

package com.volunteerx.app.explore.binder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.CharacterHelper;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

import static com.volunteerx.app.utils.Constants.NUM_CHAR_POST_CARD;
import static com.volunteerx.app.utils.Converter.NumberFormatting;

public class PostCardBinder extends ItemBinder<PostCardModel, PostCardBinder.PostViewHolder> {

    private final Context context;
    private final RequestManager glide;

    public PostCardBinder(Context context, RequestManager glide) {

        this.context = context;
        this.glide = glide;

    }

    @Override
    public PostViewHolder createViewHolder(ViewGroup parent) {
        return new PostViewHolder(inflate(parent, R.layout.layout_post_card));
    }

    @Override
    public void bindViewHolder(PostViewHolder holder, PostCardModel item) {

        ImageView mediaView = holder.mediaView;
        Button btnFollow = holder.btnFollow;

        glide.load(item.getProfilePicture())
                .into(holder.civProfilePicture);

        if (!item.getMediaUrl().isEmpty()) glide.load(item.getMediaUrl())
                .into(mediaView);

        if (!item.getTextPost().isEmpty())  holder.tvPostText.setText(item.getTextPost());

        holder.tvLikeCount.setText(NumberFormatting(item.getLikeCount()));

        holder.tvCommentCount.setText(NumberFormatting(item.getCommentCount()));

        if (item.isFollowing()) {
            btnFollow.setText(holder.itemView.getContext().getString(R.string.following));
            btnFollow.setBackground(holder.itemView.getContext().getDrawable(R.drawable.stroke_border_background_5_1));
            btnFollow.setTextColor(holder.itemView.getContext().getColor(R.color.color_following_text));

        } else {
            holder.btnFollow.setText(holder.itemView.getContext().getString(R.string.follow));
            btnFollow.setBackground(holder.itemView.getContext().getDrawable(R.drawable.rounded_border_5dp));
            btnFollow.setTextColor(holder.itemView.getContext().getColor(R.color.colorWhite));
        }


        if (item.isLiked()) {
            holder.tvLikeCount.setCompoundDrawablesWithIntrinsicBounds(holder.itemView.getContext().getDrawable(R.drawable.ic_heart_filled_red), null, null, null);
        }else {
            holder.tvLikeCount.setCompoundDrawablesWithIntrinsicBounds(holder.itemView.getContext().getDrawable(R.drawable.ic_heart_outline), null, null, null);
        }

        CharacterHelper helper = new CharacterHelper(context);

        holder.tvCharacterOne.setText(
                helper.getCharacterName(
                        item.getCharacterList()[0]
                )
        );
        holder.tvCharacterTwo.setText(
                helper.getCharacterName(
                        item.getCharacterList()[1]
                )
        );
        holder.tvCharacterOne.setBackgroundTintList(
                ColorStateList.valueOf(
                        helper.getCharacterColor(
                                item.getCharacterList()[0]
                        )
                )
        );
        holder.tvCharacterTwo.setBackgroundTintList(
                ColorStateList.valueOf(
                        helper.getCharacterColor(
                                item.getCharacterList()[1]
                        )
                )
        );
        holder.tvCharacterMore.setText(
                context.getString(R.string.more_value, item.getRemainingCharacter())
        );

        helper.recycle();

        holder.tvLikeCount.setOnClickListener(view ->  {



        });

        holder.ivMoreOptions.setOnClickListener(view -> {



        });

        btnFollow.setOnClickListener(view -> {



        });


    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof PostCardModel;
    }

    public class PostViewHolder extends ItemViewHolder<PostCardModel> {

        CircleImageView civProfilePicture;
        TextView tvName, tvPostText, tvLikeCount, tvCommentCount, tvCharacterOne, tvCharacterTwo, tvCharacterMore;
        ImageView mediaView;
        ImageView ivMoreOptions;
        Button btnFollow;


        public PostViewHolder(View itemView) {
            super(itemView);

            civProfilePicture = itemView.findViewById(R.id.civ_profile_picture);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPostText = itemView.findViewById(R.id.tv_post_text);
            tvLikeCount = itemView.findViewById(R.id.tv_post_like);
            tvCommentCount = itemView.findViewById(R.id.tv_post_comment);
            mediaView = itemView.findViewById(R.id.media_post);
            ivMoreOptions = itemView.findViewById(R.id.iv_post_card_more);
            btnFollow = itemView.findViewById(R.id.follow_btn);
            tvCharacterOne = itemView.findViewById(R.id.tv_character_one);
            tvCharacterTwo = itemView.findViewById(R.id.tv_character_two);
            tvCharacterMore = itemView.findViewById(R.id.tv_character_more);

        }
    }
}
