package com.angeltek.volunteered_trail.notification;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.NotificationProfileModel;
import com.angeltek.volunteered_trail.models.RequestProfileModel;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    private static final String TAG = "NotificationFragment";

    private RecyclerView recyclerView;
    NotificationRecyclerViewAdapter notificationRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.notification_container);

        setupRecyclerView();

        return view;

    }

    private void setupRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        notificationRecyclerViewAdapter = new NotificationRecyclerViewAdapter(getContext());



        final ArrayList<NotificationProfileModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));
        modelArrayList.add(new NotificationProfileModel("Username is working", "12hrs ago", R.drawable.avatar0));

        notificationRecyclerViewAdapter.setNotificationProfileModelArrayList(modelArrayList);
        recyclerView.setAdapter(notificationRecyclerViewAdapter);

    }

}
