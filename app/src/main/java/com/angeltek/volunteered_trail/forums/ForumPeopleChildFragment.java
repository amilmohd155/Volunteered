package com.angeltek.volunteered_trail.forums;

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
import com.angeltek.volunteered_trail.models.PeopleListModel;

import java.util.ArrayList;

public class ForumPeopleChildFragment extends Fragment {

    private static final String TAG = "ForumPeopleChildFrag";

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum_people, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.people_list_view);

        setupPeopleList();

        return view;

    }

    //Todo Incomplete layout design and click event left to configure
    private void setupPeopleList() {

        Log.d(TAG, "setupPeopleList: creating");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        ArrayList<PeopleListModel> arrayList = new ArrayList<>();

        arrayList.add(new PeopleListModel("Fullname", "username", R.drawable.avatar0, false));
        arrayList.add(new PeopleListModel("Fullname", "username", R.drawable.user, true));

        //Recyclerview Required




    }


}
