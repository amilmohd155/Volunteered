/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 7:50 PM
 *
 */

package com.volunteerx.app.MediaSwipeVew.views;

import android.util.LayoutDirection;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.volunteerx.app.MediaSwipeVew.events.IVideoPlayListener;
import com.volunteerx.app.MediaSwipeVew.media.MediaModel;
import com.volunteerx.app.MediaSwipeVew.views.fragments.EmptyViewFragment;
import com.volunteerx.app.MediaSwipeVew.views.fragments.MediaFragment;

import java.util.Collections;
import java.util.List;

public class MediaAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MediaAdapter";

    private List<MediaModel> media;

    private IVideoPlayListener videoPlayListener;

    @LayoutRes
    private int emptyView;

    public MediaAdapter(@NonNull FragmentManager fm, int behavior, List<MediaModel> media) {
        super(fm, behavior);
        this.media = media;
        Log.d(TAG, "MediaAdapter: constructor::" +media.size());
    }

    public MediaAdapter(@NonNull FragmentManager fm, int behavior, int layoutDirection, List<MediaModel> media) {
        super(fm, behavior);
        this.media = media;
        if (layoutDirection == LayoutDirection.RTL) {
            Collections.reverse(media);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: adapter getting item");
        if (media.isEmpty() && emptyView != 0) {
            return EmptyViewFragment.newInstance(emptyView);
        }
        int[] intArray = new int[]{ position, media.size()};
        return MediaFragment.newInstance(media.get(position), videoPlayListener, intArray);
    }

    @Override
    public int getCount() {
        if (media.isEmpty()) {
            if (emptyView != 0) {
                return 1;
            } else return 0;
        }
        return media.size();
    }

    public void setVideoPlayerListener(IVideoPlayListener videoPlayListener) {
        this.videoPlayListener = videoPlayListener;
    }

    public void setEmptyView(int emptyView) {
        this.emptyView = emptyView;
        notifyDataSetChanged();
    }
}
