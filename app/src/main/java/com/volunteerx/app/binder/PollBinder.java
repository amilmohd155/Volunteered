package com.volunteerx.app.binder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.PollOptionModel;
import com.volunteerx.app.utils.GlideApp;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;


public class PollBinder extends ItemBinder<PollOptionModel, PollBinder.OptionViewHolder> {

    private static final String TAG = "PollBinder";

    private Context context;
    private PollOptionModel model;

    public PollBinder(Context context) {
        this.context = context;
    }

    @Override
    public OptionViewHolder createViewHolder(ViewGroup parent) {
        return new OptionViewHolder(inflate(parent, R.layout.layout_poll_option));
    }

    @Override
    public void bindViewHolder(OptionViewHolder holder, PollOptionModel item) {

        model = item;
//        holder.etOption.setText(item.getOptionText());
//        holder.tvOptionCount.setText(String.valueOf(item.getOptionCount()));
        GlideApp.with(context)
                .load(item.getCircle())
                .into(holder.ivCircle);

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof PollOptionModel;
    }

    public class OptionViewHolder extends ItemViewHolder<PollOptionModel> {

        EditText etOption;
        TextView tvOptionCount;
        ImageView ivCircle;
        boolean isEmpty;

        public OptionViewHolder(final View itemView) {
            super(itemView);

            etOption = itemView.findViewById(R.id.option_text);
            tvOptionCount = itemView.findViewById(R.id.option_word_count);
            ivCircle = itemView.findViewById(R.id.option_circle);

            etOption.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 25) {
                        tvOptionCount.setVisibility(View.VISIBLE);
                        tvOptionCount.setText( String.valueOf(charSequence.length()) + "/30");
                    }
                    else {
                        tvOptionCount.setVisibility(View.GONE);
                    }

                    if (charSequence.length() > 0) {
                        GlideApp.with(context)
                                .load(R.drawable.circle_fill)
                                .into(ivCircle);
                        isEmpty = false;
                    }
                    else {
                        GlideApp.with(context)
                                .load(R.drawable.circle)
                                .into(ivCircle);
                        isEmpty = true;
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ivCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + getAdapterPosition() );
                    if (getAdapterPosition() > 1) {
//                        Todo remove item from recycler view
                        if (isEmpty == true) {

                        }
                    }
                }
            });

        }
    }
}
