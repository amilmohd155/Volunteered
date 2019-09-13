package com.angeltek.volunteered_trail.binder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.CharacterModel;
import com.bumptech.glide.Glide;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class CharacterBinder extends ItemBinder<CharacterModel, CharacterBinder.CharacterViewHolder> {

    Context context;

    public CharacterBinder(Context context) {
        this.context = context;
    }

    @Override
    public CharacterViewHolder createViewHolder(ViewGroup parent) {
        return new CharacterViewHolder(inflate(parent, R.layout.snippet_character_card));
    }

    @Override
    public void bindViewHolder(CharacterViewHolder holder, CharacterModel item) {
        holder.characterName.setText(item.getCharacterName());
        holder.characterCard.setCardBackgroundColor(context.getColor(item.getCharacterColor()));
        Glide.with(context)
                .load(item.getCharacterIcon())
                .into(holder.characterIcon);

    }

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleItemSelection();
                }
            });

        }
    }
}
