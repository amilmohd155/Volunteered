package com.angeltek.volunteered_trail.post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.FilePaths;
import com.angeltek.volunteered_trail.utils.FileSearch;
import com.angeltek.volunteered_trail.utils.DirectorySpinnerAdapter;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";

    //constants

    //variables
    private ArrayList<String> directories;

    //widgets
    private ImageView ivCloseBtn;
    private Button btnNext;
    private Spinner directorySpinner;
    private GridView gridView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        //init
        ivCloseBtn = (ImageView) view.findViewById(R.id.iv_closeGallery);
        btnNext = (Button) view.findViewById(R.id.btn_next);
        directorySpinner = (Spinner) view.findViewById(R.id.spinner_directory);
        gridView = (GridView) view.findViewById(R.id.gallery_gridview);
        directories = new ArrayList<>();

        //call
        setupClosingPost();
        setupNextButton();
        init();

        return view;


    }


    private void init() {
        FilePaths filePaths = new FilePaths();

        Log.d(TAG, "init: Root Directory" + filePaths.PICTURES);

        if (FileSearch.getDirectoryPaths(filePaths.PICTURES) != null) {
            directories = FileSearch.getDirectoryPaths(filePaths.PICTURES);
        }
        ArrayList<String> directoryNames = new ArrayList<>();
        for (int i = 0; i < directories.size(); ++i) {
            int index = directories.get(i).lastIndexOf("/") + 1;
            String string = directories.get(i).substring(index);
            directoryNames.add(string);
        }

        directories.add(filePaths.CAMERA);


        DirectorySpinnerAdapter adapter = new DirectorySpinnerAdapter(getActivity(),
                R.layout.layout_spinner_item, R.id.list_item, directoryNames);
        adapter.setDropDownViewResource(R.layout.layout_spinner_item);
        directorySpinner.setAdapter(adapter);

        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected: Selected" + directories.get(position));



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * Close button Connection
     */
    private void setupClosingPost() {

        ivCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Back to post activity without data");
                getActivity().onBackPressed();
            }
        });
    }

    private void setupNextButton() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Back to post activity with selected photos data");

            }
        });
    }


}
