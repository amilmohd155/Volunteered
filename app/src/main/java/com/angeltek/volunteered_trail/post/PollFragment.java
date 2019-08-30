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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.binder.PollBinder;
import com.angeltek.volunteered_trail.models.PollOptionModel;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class PollFragment extends Fragment {

    private static final String TAG = "PollFragment";

    private final int MAX_NUM_OPINIONS = 4;

    private EditText pollQuestion;
    private TextView questionCount, optionCount;
    private ImageView closeBtn;
    private LinearLayout moreOption;
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
        moreOption = view.findViewById(R.id.more_option);
        optionList = view.findViewById(R.id.option_list);
        optionModels = new ArrayList<>();

        //Initial options
        optionModels.add(new PollOptionModel(R.drawable.circle));
        optionModels.add(new PollOptionModel(R.drawable.circle));

        initViews();
        addMoreOption();
        
        return view;
    }

    private void addMoreOption() {

        moreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (optionModels.size() < MAX_NUM_OPINIONS) {

                    optionModels.add(new PollOptionModel(R.drawable.circle_fill));
                    initViews();
                    Toast.makeText(getContext(), "Add more option, size::" + optionModels.size(), Toast.LENGTH_SHORT).show();
                    if (optionModels.size() == MAX_NUM_OPINIONS) {
                        moreOption.setVisibility(View.GONE);
                    }
                }

            }
        });

    }

    private void initViews() {

        Log.d(TAG, "initViews: recycerview init");

        MultiViewAdapter adapter = new MultiViewAdapter();

        optionList.setLayoutManager(new LinearLayoutManager(getContext()));
        optionList.setAdapter(adapter);

        adapter.registerItemBinders(new PollBinder(getContext()));

        ListSection<PollOptionModel> listSection = new ListSection<>();
        listSection.addAll(optionModels);

        adapter.addSection(listSection);


    }


}
