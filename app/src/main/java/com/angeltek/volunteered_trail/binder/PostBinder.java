package com.angeltek.volunteered_trail.binder;

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
