/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:01 PM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.volunteerx.app.R;
import com.volunteerx.app.models.CharacterModelOld;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class CharacterTypeBBinder extends ItemBinder<CharacterModelOld, CharacterTypeBBinder.CharacterViewHolder>  {

    Context context;

    public CharacterTypeBBinder(Context context) {
        this.context = context;
    }

    @Override
    public CharacterTypeBBinder.CharacterViewHolder createViewHolder(ViewGroup parent) {
        return new CharacterTypeBBinder.CharacterViewHolder(inflate(parent, R.layout.character_list_item));
    }


    @Override
    public void bindViewHolder(CharacterViewHolder holder, CharacterModelOld item) {

        holder.characterName.setText(item.getCharacterName());

        Glide.with(context)
                .load(item.getCharacterIcon())
                .into(holder.characterIcon);

        GradientDrawable border = new GradientDrawable();

        if (holder.isItemSelected()) {
            holder.borderLayout.setBackgroundColor(context.getColor(R.color.colorBlack));
            holder.characterName.setTextColor(context.getColor(R.color.colorWhite));
            holder.characterIcon.setBorderWidth(2);
            holder.characterIcon.setBorderColor(context.getColor(R.color.colorWhite));
        }
        else {
            holder.borderLayout.setBackgroundColor(context.getColor(R.color.colorWhite));
            holder.characterName.setTextColor(context.getColor(R.color.colorBlack));
            holder.characterIcon.setBorderWidth(0);
        }

    }

    //Todo minimum selection 3, maximum selection 15; change color of check-mark using after min selection

    @Override
    public boolean canBindData(Object item) {
        return item instanceof CharacterModelOld;
    }

    public class CharacterViewHolder extends ItemViewHolder<CharacterModelOld> {

        TextView characterName;
        CircleImageView characterIcon;
        LinearLayout borderLayout;

        public CharacterViewHolder(View itemView) {
            super(itemView);

            characterName = itemView.findViewById(R.id.character_title);
            characterIcon = itemView.findViewById(R.id.character_icon);
            borderLayout = itemView.findViewById(R.id.border_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleItemSelection();
                }
            });

        }
    }

}
