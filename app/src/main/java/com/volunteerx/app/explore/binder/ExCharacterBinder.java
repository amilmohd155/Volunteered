/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/7/20 6:46 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/7/20 6:46 PM
 *
 */

package com.volunteerx.app.explore.binder;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

import static com.volunteerx.app.utils.Constants.CHAR_ANI;

public class ExCharacterBinder extends ItemBinder<Integer, ExCharacterBinder.ViewHolder> {

    private RequestManager glide;

    public ExCharacterBinder(RequestManager glide) {
        this.glide = glide;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(inflate(parent, R.layout.layout_ex_character));
    }

    @Override
    public void bindViewHolder(ViewHolder holder, Integer item) {

        switch (item) {
            case CHAR_ANI:
                holder.tvCharName.setText(R.string.animal);
                glide.load(R.drawable.astronaut)
                        .into(holder.ivCharArt);
                break;
                //Todo  to completed
        }

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Integer;
    }

    public class ViewHolder extends ItemViewHolder<Integer> {

        TextView tvCharName;
        ImageView ivCharArt;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCharName = itemView.findViewById(R.id.tv_character_name);
            ivCharArt = itemView.findViewById(R.id.iv_character_art);

            itemView.setOnClickListener(view -> onItemClick());

        }
    }
}
