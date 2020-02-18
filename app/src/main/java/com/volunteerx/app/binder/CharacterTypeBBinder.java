/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:01 PM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.models.CharacterModel;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class CharacterTypeBBinder extends ItemBinder<CharacterModel, CharacterTypeBBinder.CharacterViewHolder>  {

    private static final String TAG = "CharacterTypeBBinder";

    private Context context;
    private RequestManager glide;

    public CharacterTypeBBinder(Context context, RequestManager glide) {
        this.context = context;
        this.glide = glide;
    }

    @Override
    public CharacterTypeBBinder.CharacterViewHolder createViewHolder(ViewGroup parent) {
        return new CharacterTypeBBinder.CharacterViewHolder(inflate(parent, R.layout.character_list_item));
    }


    @Override
    public void bindViewHolder(CharacterViewHolder holder, CharacterModel item) {

        holder.characterName.setText(item.getCharacterNameID());

        glide.load(item.getCharacterColorID())
                .into(holder.characterIcon);

        if (holder.isItemSelected()) {
            Log.d(TAG, "bindViewHolder: " + item.getCharacterID());
            holder.borderLayout.setCardElevation(2);
            holder.borderLayout.setCardBackgroundColor(context.getColor(R.color.colorBlack));
            holder.characterName.setTextColor(context.getColor(R.color.colorWhite));
//            holder.borderLayout.setBackgroundResource(R.drawable.stroke_border_background_5_1);
//            holder.borderLayout.setBackgroundColor(context.getColor(R.color.colorVolunteerX));
        }
        else {
            holder.borderLayout.setCardElevation(0);
            holder.borderLayout.setCardBackgroundColor(context.getColor(R.color.colorWhite));
            holder.characterName.setTextColor(context.getColor(R.color.colorBlack));
//            holder.borderLayout.setBackgroundResource(R.drawable.rounded_border_5dp);
//            holder.borderLayout.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.colorWhite)));
        }

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof CharacterModel;
    }

    public class CharacterViewHolder extends ItemViewHolder<CharacterModel> {

        TextView characterName;
        CircleImageView characterIcon;
        CardView borderLayout;

        public CharacterViewHolder(View itemView) {
            super(itemView);

            characterName = itemView.findViewById(R.id.character_title);
            characterIcon = itemView.findViewById(R.id.character_icon);
            borderLayout = itemView.findViewById(R.id.border_layout);

            itemView.setOnClickListener(view -> toggleItemSelection());

        }
    }

}
