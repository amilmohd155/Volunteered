/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/13/20 1:00 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/13/20 1:00 AM
 *
 */

package com.volunteerx.app.profile.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.CharacterModel;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class UserCharacterBinder extends ItemBinder<CharacterModel, UserCharacterBinder.UCBViewHolder> {

    private Context context;

    public UserCharacterBinder(Context context) {
        this.context = context;
    }

    @Override
    public UCBViewHolder createViewHolder(ViewGroup parent) {
        return new UCBViewHolder(inflate(parent, R.layout.layout_character_swatches_small));
    }

    @Override
    public void bindViewHolder(UCBViewHolder holder, CharacterModel item) {

        holder.tvCharacterName.setText(context.getString(item.getCharacterNameID()));
        holder.cvCharacterColor.setCardBackgroundColor(context.getColor(item.getCharacterColorID()));

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof CharacterModel;
    }

    class UCBViewHolder extends ItemViewHolder<CharacterModel> {

        TextView tvCharacterName;
        CardView cvCharacterColor;

        UCBViewHolder(View itemView) {
            super(itemView);

            tvCharacterName = itemView.findViewById(R.id.tv_character);
            cvCharacterColor = itemView.findViewById(R.id.cv_character);

        }
    }
}
