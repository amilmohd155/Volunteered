package com.angeltek.volunteered_trail.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.PollOptionModel;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class PollBinder extends ItemBinder<PollOptionModel, PollBinder.OptionViewHolder> {


    @Override
    public OptionViewHolder createViewHolder(ViewGroup parent) {
        return new OptionViewHolder(inflate(parent, R.layout.layout_poll_option));
    }

    @Override
    public void bindViewHolder(OptionViewHolder holder, PollOptionModel item) {

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof PollOptionModel;
    }

    public class OptionViewHolder extends ItemViewHolder<PollOptionModel> {

        EditText etOption;
        ImageView ivCircle;
        TextView tvOptionCount;

        public OptionViewHolder(View itemView) {
            super(itemView);

            etOption = itemView.findViewById(R.id.option_text);
            tvOptionCount = itemView.findViewById(R.id.option_word_count);
            ivCircle = itemView.findViewById(R.id.option_circle);

        }
    }
}
