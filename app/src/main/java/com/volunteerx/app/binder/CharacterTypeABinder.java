/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:01 PM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.CharacterModel;
import com.bumptech.glide.Glide;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class CharacterTypeABinder extends ItemBinder<CharacterModel, CharacterTypeABinder.CharacterViewHolder> {

    Context context;

    public CharacterTypeABinder(Context context) {
        this.context = context;
    }

    @Override
    public CharacterViewHolder createViewHolder(ViewGroup parent) {
        return new CharacterViewHolder(inflate(parent, R.layout.snippet_character_card));
    }

    @Override
    public void bindViewHolder(CharacterViewHolder holder, CharacterModel item) {
        holder.characterName.setText(item.getCharacterNameID());
        holder.characterCard.setCardBackgroundColor(context.getColor(item.getCharacterColor()));
        Glide.with(context)
                .load(item.getCharacterIcon())
                .into(holder.characterIcon);

        if (holder.isItemSelected()) {
                holder.checkMark.setVisibility(View.VISIBLE);
        }
        else holder.checkMark.setVisibility(View.GONE);

    }

    //Todo minimum selection 3, maximum selection 15; change color of check-mark using after min selection

    @Override
    public boolean canBindData(Object item) {
        return item instanceof CharacterModel;
    }

    public class CharacterViewHolder extends ItemViewHolder<CharacterModel> {

        TextView characterName;
        ImageView characterIcon, checkMark;
        CardView characterCard;

        public CharacterViewHolder(View itemView) {
            super(itemView);

            characterCard = itemView.findViewById(R.id.character_card_view);
            characterName = itemView.findViewById(R.id.character_title);
            characterIcon = itemView.findViewById(R.id.character_icon);
            checkMark = itemView.findViewById(R.id.character_checkmark);

            itemView.setOnClickListener(view -> toggleItemSelection());

        }
    }
}
