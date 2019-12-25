package com.volunteerx.app.MediaSwipeVew.views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.volunteerx.app.MediaSwipeVew.events.IVideoPlayListener;
import com.volunteerx.app.MediaSwipeVew.media.ImageModel;
import com.volunteerx.app.MediaSwipeVew.media.MediaModel;
import com.volunteerx.app.MediaSwipeVew.media.VideoModel;
import com.volunteerx.app.R;


import static com.google.android.exoplayer2.Player.STATE_BUFFERING;
import static com.google.android.exoplayer2.Player.STATE_ENDED;
import static com.google.android.exoplayer2.Player.STATE_IDLE;
import static com.google.android.exoplayer2.Player.STATE_READY;
import static com.volunteerx.app.utils.Constants.VOLUME_OFF;
import static com.volunteerx.app.utils.Constants.VOLUME_ON;
import static com.volunteerx.app.utils.Converter.pTd;


//Todo loading error | retry window | ExoPlayer implementation(DASH)
public class MediaFragment extends Fragment implements Player.EventListener {

    private static final String TAG = "MediaFragment";
    private String indicatorText;
    private long currentPosition;

    private enum VolumeState {ON, OFF}

    private MediaModel media;
    private int[] intArray;
    private int volumeValue;

    private IVideoPlayListener videoPlayListener;

    private SharedPreferences sharedPreferences;

    private SimpleExoPlayer player;
    private VolumeState volumeState = VolumeState.ON;

    private PlayerView playerView;
    private ImageButton fullScreenBtn;
    private ImageView volumeControl;
    private ProgressBar videoProgressBar;
    private TextView tvIndicator;
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private CircularProgressDrawable progressDrawable;

    public MediaFragment() {
        // Required empty public constructor
    }

    public static MediaFragment newInstance(MediaModel medias, IVideoPlayListener videoPlayListener, int[] intArray) {

        MediaFragment fragment = new MediaFragment();
        fragment.setVideoPlayListener(videoPlayListener);
        Bundle args = new Bundle();
        args.putParcelable("media", medias);
        args.putIntArray("intArray", intArray);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            media = getArguments().getParcelable("media");
            intArray = getArguments().getIntArray("intArray");
            indicatorText = (intArray[0] + 1) + "/" + intArray[1];
        }catch (NullPointerException e) {
            e.getStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: media fragment created");

        if (media != null) {

            if (media instanceof ImageModel) {

                relativeLayout = new RelativeLayout(getActivity());
                imageView = new ImageView(getActivity());
                progressDrawable = new CircularProgressDrawable(getActivity());
                tvIndicator = new TextView(getActivity());

                setupImageViewUI();

                ImageModel imageModel = (ImageModel) media;
                imageView.setScaleType(ImageView.ScaleType.CENTER);

                if (imageModel.getErrorDrawable() == null) {
                    Glide.with(getActivity())
                            .load(imageModel.getUrl())
                            .apply(new RequestOptions()
                                    .placeholder(progressDrawable))
                            .into(imageView);
                }else {
                    if (imageModel.getErrorDrawable() != null) {
                        Glide.with(getActivity())
                                .load(imageModel.getUrl())
                                .apply(new RequestOptions()
                                        .placeholder(progressDrawable)
                                        .error(imageModel.getErrorDrawable())) // error image must be considered for change
                                .into(imageView);
                    }
                }

                return relativeLayout;

            }
            else if (media instanceof VideoModel) {

                playerView = new PlayerView(getActivity());

                sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

                TrackSelection.Factory selection = new AdaptiveTrackSelection.Factory();
                TrackSelector selector = new DefaultTrackSelector(selection);

                player = ExoPlayerFactory.newSimpleInstance(getActivity(), selector);

                playerView.setPlayer(player);

                volumeControl = playerView.findViewById(R.id.exo_volume_control);
                fullScreenBtn = playerView.findViewById(R.id.exo_full_screen);
                videoProgressBar = playerView.findViewById(R.id.exo_loading);
                tvIndicator = playerView.findViewById(R.id.indicator);

                String indicatorText = (intArray[0]+1) + "/" + intArray[1];
                tvIndicator.setText(indicatorText);

                //layout transition
//                playerView.setLayoutTransition(new LayoutTransition());
                playerView.setUseController(true);


                MediaSource mediaSource = new ProgressiveMediaSource.Factory(
                        new DefaultHttpDataSourceFactory(Util.getUserAgent(getActivity(), getString(R.string.app_name))))
                        .createMediaSource(((VideoModel) media).getUri());

                player.setRepeatMode(Player.REPEAT_MODE_ALL);
                player.prepare(mediaSource, true, false);

                //setup default volume value
                defaultVolume();

                playerView.getVideoSurfaceView().setOnClickListener((View v)-> {
                        toggleControlUI();
                        toggleVolume();
                });

                fullScreenBtn.setOnClickListener((View v) -> {
                        //fullscreen event

                });

                return playerView;

            }else {
                throw new RuntimeException("Unknown Media kind");
            }

        } else {
            throw new RuntimeException("Media is null");
        }

    }

    private void setupImageViewUI() {

        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));


        tvIndicator.setText(indicatorText);

        int paddingBT = pTd(getContext(), 5);
        int paddingLR = pTd(getContext(), 10);

        tvIndicator.setPadding(paddingLR, paddingBT, paddingLR, paddingBT);

        tvIndicator.setBackground(getContext().getDrawable(R.drawable.gradient_color));
        tvIndicator.setTextColor(getContext().getColor(R.color.colorWhite));

        // Defining the layout parameters of the TextView
        RelativeLayout.LayoutParams tvLayoutParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        tvLayoutParam.setMargins(0, pTd(getContext(), 10), pTd(getContext(), 10) , 0);
        tvLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_END);

        // Setting the parameters on the TextView
        tvIndicator.setLayoutParams(tvLayoutParam);

        relativeLayout.addView(imageView);
        relativeLayout.addView(tvIndicator);

        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));


    }

    private void defaultVolume() {

        volumeValue = sharedPreferences.getInt(getString(R.string.media_volume_key), 1);

        Log.d(TAG, "defaultVolume: " + volumeValue);

        switch (volumeValue) {
            case VOLUME_OFF:
                player.setVolume(0f);
                volumeState = VolumeState.OFF;
                animateVolumeControl();
                break;
            case VOLUME_ON:
                player.setVolume(1f);
                volumeState = VolumeState.ON;
                animateVolumeControl();
                break;
        }

    }

    private void toggleControlUI() {
        if (playerView != null) {
            playerView.showController();
        }
    }

    private void toggleVolume() {

        SharedPreferences.Editor editor;

        if (player != null) {
            if (volumeState == VolumeState.ON) {
                Log.d(TAG, "toggleVolume: disabling volume");
                volumeState = VolumeState.OFF;
                player.setVolume(0f);

                editor = sharedPreferences.edit()
                        .putInt(getString(R.string.media_volume_key), VOLUME_OFF);
                editor.apply();

                animateVolumeControl();

            }else if (volumeState == VolumeState.OFF){
                Log.d(TAG, "toggleVolume: enabling volume");
                volumeState = VolumeState.ON;

                player.setVolume(1f);

                editor = sharedPreferences.edit()
                        .putInt(getString(R.string.media_volume_key), VOLUME_ON);
                editor.apply();

                animateVolumeControl();

            }
        }
    }

    private void animateVolumeControl() {

        if (volumeControl != null) {

            volumeControl.bringToFront();
            if (volumeState == VolumeState.OFF) {
                //Todo volume off icon
                volumeControl.setImageDrawable(getContext().getDrawable(R.drawable.ic_close));
            }
            else if (volumeState == VolumeState.ON) {
                //Todo volume on icon
                volumeControl.setImageDrawable(getContext().getDrawable(R.drawable.ic_volume_on));
            }

            //animateHere

        }

    }


    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case STATE_ENDED: {
                Log.d(TAG, "onPlayerStateChanged: Video ended");
                player.seekTo(0);
            }
            break;
            case STATE_BUFFERING: {
                Log.d(TAG, "onPlayerStateChanged: Buffering video");
                if (videoProgressBar != null && videoProgressBar.getVisibility() == View.GONE) {
                    videoProgressBar.setVisibility(View.VISIBLE);
                    tvIndicator.setVisibility(View.GONE);
                    playerView.showController();
                }
                currentPosition = player.getCurrentPosition();
            }
            break;
            case STATE_READY: {
                Log.d(TAG, "onPlayerStateChanged: Ready to play");
                if (videoProgressBar != null && videoProgressBar.getVisibility() == View.VISIBLE) {
                    videoProgressBar.setVisibility(View.GONE);
                    tvIndicator.setVisibility(View.VISIBLE);
                }
                playerView.setControllerShowTimeoutMs(2000);
            }
            break;
            case STATE_IDLE: {
                Log.d(TAG, "onPlayerStateChanged: player in idle state");
            }break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            videoPlayListener.onVideoStopped();
            player.setPlayWhenReady(false);
            player.addListener(this);
            defaultVolume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            videoPlayListener.onVideoStarted();
            if (player.getPlaybackState() == STATE_ENDED) {
                player.seekTo(0);
            }
            player.setPlayWhenReady(true);
            player.addListener(this);
            defaultVolume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            videoPlayListener = null;
        }
    }

    private void setVideoPlayListener(IVideoPlayListener videoPlayListener) {
        this.videoPlayListener = videoPlayListener;
    }

}
