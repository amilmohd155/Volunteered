/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/10/20 9:46 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/10/20 9:46 PM
 *
 */

package com.volunteerx.app.binder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.ButtonModel;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class ButtonBinder extends ItemBinder<ButtonModel, ButtonBinder.ButtonVH> {

    public ButtonBinder(Context context) {
        this.context = context;
    }

    private Context context;

    @Override
    public ButtonVH createViewHolder(ViewGroup parent) {
        return new ButtonVH(new Button(context));
    }

    @Override
    public void bindViewHolder(ButtonVH holder, ButtonModel item) {

        holder.button.setText(item.getButtonText());
        if (item.getButtonDrawable() != 0) {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(item.getButtonDrawable()), null, null, null);
        }

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof ButtonModel;
    }

    public class ButtonVH extends ItemViewHolder<ButtonModel> {

        Button button;

        public ButtonVH(View itemView) {
            super(itemView);

            button = (Button) itemView;
            button.setTextColor(context.getColor(R.color.colorWhite));
            button.setTypeface(Typeface.DEFAULT_BOLD);
            button.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.colorVolunteerX)));
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }
    }
}
