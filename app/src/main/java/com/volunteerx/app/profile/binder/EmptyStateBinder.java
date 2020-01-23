/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/22/20 1:42 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/22/20 1:42 AM
 *
 */

package com.volunteerx.app.profile.binder;

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;


public class EmptyStateBinder extends ItemBinder<String, ItemViewHolder<String>> {

    @LayoutRes
    private int layout;


    public EmptyStateBinder( int layout) {

        this.layout = layout;

    }

    @Override
    public ItemViewHolder<String> createViewHolder(ViewGroup parent) {
        return new ItemViewHolder<>(inflate(parent, layout));
    }

    @Override
    public void bindViewHolder(ItemViewHolder<String> holder, String item) {
        //nothing to bind
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof String;
    }
}
