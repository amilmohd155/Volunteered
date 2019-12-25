package com.volunteerx.app.MediaSwipeVew.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.volunteerx.app.MediaSwipeVew.events.IVideoPlayListener;
import com.volunteerx.app.MediaSwipeVew.media.MediaModel;
import com.volunteerx.app.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MediaSwipeView extends FrameLayout implements ViewPager.OnPageChangeListener, IVideoPlayListener {

    private static final String TAG = "MediaSwipeView";

    private List<MediaModel> mediaModels = new ArrayList<>();

    private AppCompatActivity hostActivity;
    private CustomViewPager viewPager;

    private boolean mustWrapContent;

    private boolean setupIsCalled = false;
    List<MediaModel> mediaQueue = new ArrayList<>();
    private int viewPagerHeight = 0;

    private MediaAdapter adapter;

    @LayoutRes
    private int emptyView = R.layout.fragment_empty_media;

    public MediaSwipeView(@NonNull Context context) {
        super(context);
    }

    public MediaSwipeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseCustomAttributes(attrs);
    }

    public MediaSwipeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseCustomAttributes(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MediaSwipeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseCustomAttributes(attrs);
    }

    private void parseCustomAttributes(AttributeSet attrs) {
        if (attrs != null) {

        }
        if (!isInEditMode()) {
            setup();
        }
    }

    private void setup() {

        Log.d(TAG, "setup: ");
        
        if (!isInEditMode()) {
            post(()->{
                    Log.d(TAG, "run: Running setup MediaSwipeView");

                    if (getContext() instanceof AppCompatActivity) {
                        hostActivity = (AppCompatActivity) getContext();
                    }else throw new RuntimeException("host must extend AppCompatActivity");

                    //made change
                    mustWrapContent = getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT;
                    viewPager = new CustomViewPager(getContext(), viewPagerHeight);

                    viewPager.setId(View.generateViewId());

                    viewPager.addOnPageChangeListener(MediaSwipeView.this);

                    addView(viewPager);

                    setupIsCalled = true;
                    renderRemainingMedia();

            });
        }

    }

    private void renderRemainingMedia() {

        setMedia(mediaQueue);

    }

    public void setMedia(List<MediaModel> mediaModels) {

        Log.d(TAG, "setMedia: media is been setup");

        if (setupIsCalled) {

            this.mediaModels = mediaModels;

            Log.d(TAG, "setMedia: " + mediaModels.size());

            int i = 0;
            for (MediaModel media : mediaModels) {
                media.setPosition(i);
                i++;
            }

            adapter = new MediaAdapter(hostActivity.getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mediaModels);

            adapter.setVideoPlayerListener(this);

            viewPager.setAdapter(adapter);

        }
        else {

            mediaQueue.addAll(mediaModels);

        }

    }

    public  void setViewPagerHeight(int viewPagerHeight) {

        this.viewPagerHeight = viewPagerHeight;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING: {
                //when scrolling
                Log.d(TAG, "onPageScrollStateChanged: dragging");
            }break;
            case ViewPager.SCROLL_STATE_IDLE: {
                //when done scrolling
            }break;
        }
    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoStopped() {

    }


    public void setCurrentSlide(final int position) {
        post(()-> {
                if (viewPager != null) {
                    viewPager.setCurrentItem(position);
                }
        });
    }

    public int getCurrentSlidePosition() {
        if (viewPager != null) {
            return -1;
        }
        return viewPager.getCurrentItem();
    }


}
