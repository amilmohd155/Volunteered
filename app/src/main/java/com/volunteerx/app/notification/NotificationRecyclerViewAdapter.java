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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.NotificationProfileModel;

import java.util.ArrayList;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<NotificationProfileModel> notificationProfileModelArrayList;

    public NotificationRecyclerViewAdapter(Context context) {
        this.context = context;
        notificationProfileModelArrayList = new ArrayList<>();
    }

    public void setNotificationProfileModelArrayList(ArrayList<NotificationProfileModel> notificationProfileModelArrayList) {
        this.notificationProfileModelArrayList = notificationProfileModelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;

        switch (i) {
            case 0:
                view = LayoutInflater.from(context).inflate(
                        R.layout.layout_notification_item,
                        viewGroup,
                        false
                );
                return new ViewHolderCard(view);
            case 2:
                view = LayoutInflater.from(context).inflate(
                        R.layout.layout_chronometer_notification,
                        viewGroup,
                        false
                );
                return new ViewHolderHeading(view);
        }

        return null;
    }

    /**
     * logic for putting chronoheading in place
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        return position % 2 * 2;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (notificationProfileModelArrayList != null) {

            NotificationProfileModel notificationProfileModel = notificationProfileModelArrayList.get(i);

            switch (viewHolder.getItemViewType()) {
                case 0: {// Setting value for card
                    ViewHolderCard viewHolderCard = (ViewHolderCard) viewHolder;
                    viewHolderCard.bindValue(notificationProfileModel);
                    break;
                }
                case 1: {//Setting chronologic value @param chronomical data calculated
                    ViewHolderHeading viewHolderHeading = (ViewHolderHeading) viewHolder;
                    viewHolderHeading.bindValue();
                }
            }

        }

    }


    @Override
    public int getItemCount() {
        return notificationProfileModelArrayList.size();
    }

    private class ViewHolderCard extends RecyclerView.ViewHolder {

        private TextView tvNotificationDescription;
        private TextView tvTimestamp;
        private ImageView IvUserPhoto;

        private ViewHolderCard(@NonNull View itemView) {
            super(itemView);

            tvNotificationDescription = itemView.findViewById(R.id.notification_desc);
            tvTimestamp = itemView.findViewById(R.id.timestamp);
            IvUserPhoto = itemView.findViewById(R.id.circleImageView);

        }

        private void bindValue(@NonNull NotificationProfileModel notificationProfileModel) {

            IvUserPhoto.setImageResource(notificationProfileModel.getProfilePhotoPath());
            tvNotificationDescription.setText(notificationProfileModel.getNotificationDescription());
            tvTimestamp.setText(notificationProfileModel.getTimestamp());

        }
    }

    private class ViewHolderHeading extends RecyclerView.ViewHolder {

        private TextView tvChronoHeading;


        public ViewHolderHeading(@NonNull View itemView) {
            super(itemView);

            tvChronoHeading = itemView.findViewById(R.id.chrono_reading);
        }

        private void bindValue() {

            tvChronoHeading.setText("A week ago");

        }
    }
}
