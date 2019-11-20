package com.volunteerx.app.notification;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volunteerx.app.R;
import com.volunteerx.app.models.NotificationProfileModel;

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
