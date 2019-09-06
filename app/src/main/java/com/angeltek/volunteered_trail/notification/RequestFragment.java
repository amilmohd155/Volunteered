package com.angeltek.volunteered_trail.notification;

import android.content.Context;
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

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.RequestProfileModel;

import java.util.ArrayList;

public class RequestFragment extends Fragment {

    Context context = getContext();

    //Widgets
    private RecyclerView recyclerView;
    private RequestRecyclerViewAdapter requestRecyclerViewAdapter;

    private static final String TAG = "RequestFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_request, container, false);

        recyclerView = view.findViewById(R.id.request_container);

        setupRecyclerView();

        return view;

    }

    private void setupRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        requestRecyclerViewAdapter = new RequestRecyclerViewAdapter(getContext());



        final ArrayList<RequestProfileModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new RequestProfileModel("Fullname", "@Username", R.drawable.avatar0));

        requestRecyclerViewAdapter.setRequestProfileModelArrayList(modelArrayList);
        recyclerView.setAdapter(requestRecyclerViewAdapter);

    }
}
