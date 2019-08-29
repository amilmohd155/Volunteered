package com.angeltek.volunteered_trail.post;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.PollOptionModel;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class PollFragment extends Fragment {

    private static final String TAG = "PollFragment";

    private EditText pollQuestion;
    private TextView questionCount, optionCount;
    private ImageView closeBtn;
    private RecyclerView optionList;
    private ArrayList<PollOptionModel> optionModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_poll, container, false);

        closeBtn = view.findViewById(R.id.close_poll);
        pollQuestion = view.findViewById(R.id.poll_question);
        questionCount = view.findViewById(R.id.question_count);
        optionList = view.findViewById(R.id.option_list);
        optionModels = new ArrayList<>();

        //Initial options
        optionModels.add(new PollOptionModel());
        optionModels.add(new PollOptionModel());

        initViews();
        
        return view;
    }

    private void initViews() {

        MultiViewAdapter adapter = new MultiViewAdapter();

        optionList.setLayoutManager(new LinearLayoutManager(getContext()));
        optionList.setAdapter(adapter);

        ListSection<PollOptionModel> listSection = new ListSection<>();
        listSection.addAll(optionModels);

    }


}
