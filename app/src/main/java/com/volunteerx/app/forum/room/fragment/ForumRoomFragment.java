/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/29/20 9:21 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/29/20 9:20 PM
 *
 */

package com.volunteerx.app.forum.room.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.R;
import com.volunteerx.app.activity.ActivityFragment;
import com.volunteerx.app.forum.info.ForumInfoFragment;
import com.volunteerx.app.forum.room.binder.ForumChatBinder;
import com.volunteerx.app.forum.room.model.Conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

//Todo make recyclerview with image, video, document support
public class ForumRoomFragment extends Fragment implements View.OnClickListener  {

    private static final String TAG = "ForumRoomFragment";

    //Constants

    //Variables

    private EditText etText;
    private BottomNavigationViewEx attachmentTab;
    private RecyclerView chatList;
    private String textMessage;
    private MultiViewAdapter adapter;
    private Conversation conversation;
    private List<Conversation> conversationList = new ArrayList<>();
    private ListSection<Conversation> listSection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BigImageViewer.initialize(GlideImageLoader.with(getContext()));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum_room, container, false);

        //Widgets
        attachmentTab = view.findViewById(R.id.attachment_tab);
        chatList = view.findViewById(R.id.chat_list);
        etText = view.findViewById(R.id.et_text);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        view.findViewById(R.id.attachment_pop_btn).setOnClickListener(this);
        view.findViewById(R.id.forum_info_btn).setOnClickListener(this);
        view.findViewById(R.id.send_btn).setOnClickListener(this);
        view.findViewById(R.id.title_layout).setOnClickListener(this);


        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null)
                getActivity().onBackPressed();
            else if(getParentFragment() != null) {
                //
            }
        });


        setupBottomNavigationView(attachmentTab);

        setupForumRecyclerView();

        return view;

    }


    public void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {

        Log.d(TAG, "setupBottomNavigationView: Setting up Bottom Navigation View");

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.photo: {
                    MediaSelectBSFragment fragment = MediaSelectBSFragment.newInstance(R.layout.fragment_media_bottom_sheet);
                    fragment.show(getChildFragmentManager(), fragment.getTag());
                }
                    break;
                case R.id.camera:{
                    //
                }
                    break;
                case R.id.file: {
                    //da
                }
                break;

            }

            return false;
        });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attachment_pop_btn: {
                if (attachmentTab.getVisibility() == View.GONE) attachmentTab.setVisibility(View.VISIBLE);
                else if (attachmentTab.getVisibility() == View.VISIBLE) attachmentTab.setVisibility(View.GONE);
            }break;

            case R.id.back_btn:{
                if (getActivity()!= null) {
                    getActivity().onBackPressed();
                }
            }break;

            case R.id.forum_info_btn: {
                goToForumInfoFragment();
                break;
            }
            case R.id.send_btn:
                getMessageText();
                break;
            case R.id.title_layout:
                goToProfileFragment();
                break;
        }
    }


    private void goToForumInfoFragment() {

        Log.d(TAG, "goToForumInfoFragment: creating info fragment");
        try {

            FragmentTransaction fragmentTransaction = getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.forum_room_container, new ForumInfoFragment(), getResources().getString(R.string.forum_info_fargment))
                    .addToBackStack(null);
            fragmentTransaction.commit();

        }catch (NullPointerException ex) {
            Log.d(TAG, "goToForumInfoFragment: Error encountered in fragment Transaction" + ex);
        }

    }

    private void setupForumRecyclerView() {

        adapter = new MultiViewAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.scrollToPosition(0);

        chatList.setLayoutManager(manager);
        chatList.setAdapter(adapter);


        adapter.registerItemBinders(new ForumChatBinder(Glide.with(this), getContext()));

//        conversationList.add(new Conversation("chikkuRoy123",
//                "https://www.gstatic.com/webp/gallery/3.jpg",
//                1579190967790L,
//                "Lorem Ipsum",
//                "https://i.postimg.cc/jds3hj3n/Screenshot-15.png"));
//
//        conversationList.add(new Conversation("chikkuRoy123",
//                "https://i.postimg.cc/dVVXJ08c/Screenshot-9.png",
//                1579191119623L,
//                "Lorem Ipsum",
//                null));

        listSection = new ListSection<>();
        listSection.addAll(conversationList);

        adapter.addSection(listSection);

        if(!conversationList.isEmpty())
            chatList.smoothScrollToPosition(listSection.size() - 1);

        chatList.addOnLayoutChangeListener((view, i, i1, i2, bottom, i4, i5, i6, oldBottom) -> {
            if (bottom < oldBottom) {
                chatList.postDelayed(() -> {
                    if (chatList.getAdapter().getItemCount() != 0 )
                        chatList.smoothScrollToPosition(chatList.getAdapter().getItemCount() - 1);
                }, 0);
            }
        });

    }

    private void getMessageText() {

        if (!etText.getText().toString().isEmpty()) {

            textMessage = etText.getText().toString().trim();

            conversation = new Conversation("docren",
                    "https://i.postimg.cc/SRGTh0RK/Screenshot-12.png",
                    new Date().getTime(),
                    textMessage,
                    null);

            listSection.add(conversation);

            adapter.notifyItemInserted(listSection.size() - 1);
            chatList.smoothScrollToPosition(listSection.size() - 1);

            if (etText.length() > 0)
                etText.getText().clear();

        }

    }

    private void goToProfileFragment() {

        getFragmentManager().beginTransaction()
                .replace(R.id.forum_room_container, ActivityFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }
}
