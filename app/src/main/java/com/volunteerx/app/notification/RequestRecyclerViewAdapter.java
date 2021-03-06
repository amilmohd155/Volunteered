/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.notification;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.volunteerx.app.R;
import com.volunteerx.app.models.RequestProfileModel;

import java.util.ArrayList;


public class RequestRecyclerViewAdapter extends RecyclerView.Adapter<RequestRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RequestRecyclerViewAdap";

    private Context context;
    private ArrayList<RequestProfileModel> requestProfileModelArrayList;

    public RequestRecyclerViewAdapter(Context context) {
        this.context = context;
        requestProfileModelArrayList = new ArrayList<>();
    }

    public void setRequestProfileModelArrayList(ArrayList<RequestProfileModel> requestProfileModelArrayList) {
        this.requestProfileModelArrayList = requestProfileModelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_request_item,
                viewGroup,
                false
        );

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RequestRecyclerViewAdapter.ViewHolder myViewHolder, int i) {

        RequestProfileModel requestProfileModel = requestProfileModelArrayList.get(i);

        myViewHolder.IvUserPhoto.setImageResource(requestProfileModel.getProfilePhotoPath());
        myViewHolder.tvFullName.setText(requestProfileModel.getName());
        myViewHolder.tvUserName.setText(requestProfileModel.getTimestamp());

        myViewHolder.setupOnclick(i);

    }

    @Override
    public int getItemCount() {
        return requestProfileModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFullName;
        private TextView tvUserName;
        private ImageView IvUserPhoto;
        private LinearLayout declineLayout;
        private LinearLayout acceptLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.full_name);
            tvUserName = itemView.findViewById(R.id.username);
            IvUserPhoto = itemView.findViewById(R.id.circleImageView);
            declineLayout = itemView.findViewById(R.id.decline_request);
            acceptLayout = itemView.findViewById(R.id.accept_request);

        }

        private void setupOnclick(final int i) {

            declineLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d(TAG, "onClick: decline clicked");

                    Toast.makeText(context, "Decline Clicked:: " + String.valueOf(i) , Toast.LENGTH_SHORT).show();

                }
            });

            acceptLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d(TAG, "onClick: accept clicked");

                    Toast.makeText(context, "Accept Clicked:: " + String.valueOf(i) , Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
