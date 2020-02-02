/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/29/20 9:23 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/29/20 9:20 PM
 *
 */

package com.volunteerx.app.forum.room.binder;

import android.view.View;
import android.view.ViewGroup;

import com.volunteerx.app.forum.room.model.ChatInfoModel;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class ForumDetailsBinder extends ItemBinder<ChatInfoModel, ForumDetailsBinder.ChatInfoVideoHolder> {
    @Override
    public ChatInfoVideoHolder createViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void bindViewHolder(ChatInfoVideoHolder holder, ChatInfoModel item) {

    }

    @Override
    public boolean canBindData(Object item) {
        return false;
    }

    public class ChatInfoVideoHolder extends ItemViewHolder<ChatInfoModel> {
        public ChatInfoVideoHolder(View itemView) {
            super(itemView);
        }
    }
}
