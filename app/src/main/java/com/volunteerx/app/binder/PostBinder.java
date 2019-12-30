/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.binder;

import android.view.ViewGroup;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class PostBinder extends ItemBinder {
    @Override
    public ItemViewHolder createViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void bindViewHolder(ItemViewHolder holder, Object item) {

    }

    @Override
    public boolean canBindData(Object item) {
        return false;
    }
}
