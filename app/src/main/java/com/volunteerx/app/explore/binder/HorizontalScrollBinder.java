/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/25/20 9:12 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/25/20 9:12 PM
 *
 */

package com.volunteerx.app.explore.binder;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.volunteerx.app.R;
import com.volunteerx.app.explore.model.ActivityCardModel;
import com.volunteerx.app.explore.model.EventCardModel;

import java.util.ArrayList;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class HorizontalScrollBinder extends ItemBinder<Integer, HorizontalScrollBinder.HSViewHolder> {

    private ArrayList<EventCardModel> eventCardModels;
    private RequestManager glide;
    private ArrayList<ActivityCardModel> activityCardModels;
    private RecyclerView.RecycledViewPool viewPool;

    @SuppressWarnings("unchecked")
    public <T>HorizontalScrollBinder(RequestManager glide, ArrayList<T> model) {
        this.glide = glide;
        if (model.get(0) instanceof ActivityCardModel)
            this.activityCardModels = (ArrayList<ActivityCardModel>)model;
        else if(model.get(0) instanceof EventCardModel)
            this.eventCardModels = (ArrayList<EventCardModel>) model;

        viewPool = new RecyclerView.RecycledViewPool();

    }

    @Override
    public HSViewHolder createViewHolder(ViewGroup parent) {
        return new HSViewHolder(inflate(parent, R.layout.fragment_recycler_view_only));
    }



    @Override
    public void bindViewHolder(HSViewHolder holder, Integer item) {

        MultiViewAdapter adapter = new MultiViewAdapter();
        adapter.unRegisterAllItemBinders();

        if (activityCardModels != null) {

            ActivityCardBinder activityCardBinder = new ActivityCardBinder(holder.itemView.getContext(), glide);

            adapter.registerItemBinders(activityCardBinder);

            ListSection<ActivityCardModel> activityCardModelSection = new ListSection<>();
            activityCardModelSection.addAll(activityCardModels);

            adapter.addSection(activityCardModelSection);
            adapter.setHasStableIds(true);

            holder.recyclerView.setRecycledViewPool(viewPool);
            holder.recyclerView.setAdapter(adapter);

        }

    }


    @Override
    public boolean canBindData(Object item) {
        return item instanceof Integer;
    }

    public class HSViewHolder extends ItemViewHolder<Integer> {

        RecyclerView recyclerView;

        public HSViewHolder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setItemViewCacheSize(10);

        }
    }
}
