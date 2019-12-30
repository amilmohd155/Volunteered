/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 8:10 PM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.utils.ClickListener;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class CharacterBinder extends ItemBinder<CharacterModel, CharacterBinder.CharacterViewHolder> {

    private Context context;
    private ClickListener listener;

    public CharacterBinder(Context context, ClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public CharacterBinder.CharacterViewHolder createViewHolder(ViewGroup parent) {
        return new CharacterViewHolder(inflate(parent, R.layout.layout_character_swatches));
    }

    @Override
    public void bindViewHolder(CharacterBinder.CharacterViewHolder holder, final CharacterModel item) {

        holder.tvCharacterName.setText(context.getString(item.getCharacterNameID()));

        if (holder.isItemSelected()) {

            holder.cvCharacterColor.setCardBackgroundColor(context.getColor(item.getCharacterColorID()));

        }
        else  {
            holder.cvCharacterColor.setCardBackgroundColor(context.getColor(R.color.colorCharacterDeselected));
        }

        holder.itemView.setOnLongClickListener((View view) -> listener.onLongClick(item.getCharacterID()));


    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof CharacterModel;
    }

    public class CharacterViewHolder extends ItemViewHolder<CharacterModel> {

        TextView tvCharacterName;
        CardView cvCharacterColor;

        public CharacterViewHolder(final View itemView) {
            super(itemView);

            tvCharacterName = itemView.findViewById(R.id.tv_character);
            cvCharacterColor = itemView.findViewById(R.id.cv_character);

            itemView.setOnClickListener((View v) -> toggleItemSelection());

        }
    }
}
