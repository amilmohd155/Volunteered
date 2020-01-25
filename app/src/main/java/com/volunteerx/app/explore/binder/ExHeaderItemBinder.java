/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 11:14 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 11:14 AM
 *
 */

package com.volunteerx.app.explore.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.explore.model.ExHeaderTextModel;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class ExHeaderItemBinder extends ItemBinder<ExHeaderTextModel, ExHeaderItemBinder.TextInfoViewHolder> {
    @Override
    public TextInfoViewHolder createViewHolder(ViewGroup parent) {
        return new TextInfoViewHolder(inflate(parent, R.layout.snippet_ex_text_header));
    }

    @Override
    public void bindViewHolder(TextInfoViewHolder holder, ExHeaderTextModel item) {

        holder.tvMainHeading.setText(item.getMainHeading());
        holder.tvNavHeading.setText(item.getNavHeading());

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof ExHeaderTextModel;
    }



    public class TextInfoViewHolder extends ItemViewHolder<ExHeaderTextModel> {

        TextView tvMainHeading, tvNavHeading;

        public TextInfoViewHolder(View itemView) {
            super(itemView);

            tvMainHeading = itemView.findViewById(R.id.tv_main_heading);
            tvNavHeading = itemView.findViewById(R.id.tv_nav_heading);

        }
    }
}
