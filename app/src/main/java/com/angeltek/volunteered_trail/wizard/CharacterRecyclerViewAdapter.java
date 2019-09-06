package com.angeltek.volunteered_trail.wizard;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.CharacterModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "CharacterRecyclerViewAdapter";

    ArrayList<CharacterModel> mValues;
    Context context;
    protected ItemListener itemListener;

    public CharacterRecyclerViewAdapter(ArrayList<CharacterModel> mValues, Context context, ItemListener itemListener) {
        this.mValues = mValues;
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.snippet_character_card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(mValues.get(position));
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(CharacterModel characterModel, ImageView checkbox);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView characterName;
        private ImageView characterIcon;
        private CardView characterCard;
        private ImageView checkBox;

        CharacterModel characterModel;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            characterName = itemView.findViewById(R.id.character_title);
            characterIcon = itemView.findViewById(R.id.character_icon);
            characterCard = itemView.findViewById(R.id.character_card_view);
            checkBox = itemView.findViewById(R.id.character_checkbox);
        }

        private void setData(CharacterModel characterModel) {

            this.characterModel = characterModel;

            characterName.setText(characterModel.getCharacterName());
            characterCard.setCardBackgroundColor(ContextCompat.getColor(context,characterModel.getCharacterColor()));
            Glide.with(context)
                    .load(characterModel.getCharacterIcon())
                    .centerCrop()
                    .into(characterIcon);

        }

        @Override
        public void onClick(View v) {
            if (itemListener != null) {
                itemListener.onItemClick(characterModel, checkBox);
            }
        }
    }

}
