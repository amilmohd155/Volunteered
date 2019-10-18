package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.PeopleListModel;

import java.util.ArrayList;

import mva2.adapter.MultiViewAdapter;

public class PeopleFragment extends Fragment {

    private static final String TAG = "PeopleFragment";

    //UI
    private RecyclerView recyclerView;

    //Variable
    private ArrayList<PeopleListModel> model = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_people, container, false);

        recyclerView = view.findViewById(R.id.people_list_view);

        model.add(new PeopleListModel("ABC", "abc123", R.drawable.car_wan, true, 1));
        model.add(new PeopleListModel("CBE", "cbe344", R.drawable.astronaut, true, 1));
        model.add(new PeopleListModel("ASD", "asd668", R.drawable.avatar0, false,1));
        model.add(new PeopleListModel("QWE", "qwe490", R.drawable.car_wan, true, 1));

        initView();

        return view;

    }

    private void initView() {

        Log.d(TAG, "initView: initializing recycler_view");

        MultiViewAdapter adapter = new MultiViewAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }
}
