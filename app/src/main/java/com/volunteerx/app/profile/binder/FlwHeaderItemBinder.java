/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/22/20 9:42 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/22/20 9:42 AM
 *
 */

package com.volunteerx.app.profile.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volunteerx.app.R;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class FlwHeaderItemBinder extends ItemBinder<String, FlwHeaderItemBinder.TextInfoViewHolder> {

    @Override
    public TextInfoViewHolder createViewHolder(ViewGroup parent) {
        return new TextInfoViewHolder(inflate(parent, R.layout.layout_text_info));
    }

    @Override
    public void bindViewHolder(TextInfoViewHolder holder, String item) {

        holder.tvTextInfo.setText(item);

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof String;
    }

    public class TextInfoViewHolder extends ItemViewHolder<String> {

        TextView tvTextInfo;

        public TextInfoViewHolder(View itemView) {
            super(itemView);

            tvTextInfo = itemView.findViewById(R.id.tv_text_info);

        }
    }
}
