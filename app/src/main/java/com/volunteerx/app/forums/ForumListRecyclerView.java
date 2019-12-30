/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.forums;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.ForumListModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

//Todo Correct recyclerview( not showing first element)

public class ForumListRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ForumListRecyclerView";
    
    //added view types
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private ArrayList<ForumListModel> forumListModels;


    public ForumListRecyclerView(Context context, ArrayList<ForumListModel> forumListModels) {
        this.context = context;
        this.forumListModels = forumListModels;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.snippet_forum_list_item, viewGroup, false);
            return new ItemViewHolder(view);
        }
        else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.snippet_search_bar_view_top, viewGroup, false);
            return new HeaderViewHolder(view);
        }

        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (!isPositionHeader(position)) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            itemViewHolder.setData(forumListModels.get(position - 1));
        }

    }


    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return forumListModels.size() + 1; // header
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    //added a method to check if given position is a header
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    /**
     * ItemViewHolder Used for list item holder
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profilePictureCv, lastMessageProfilePictureCv;
        private TextView activityNameTv,activeStateTv, lastMessageTextTv;

        private ForumListModel forumListModel;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePictureCv = (CircleImageView) itemView.findViewById(R.id.forum_profile_photo);
            lastMessageProfilePictureCv = (CircleImageView) itemView.findViewById(R.id.last_message_profile_photo);
            activityNameTv  = (TextView) itemView.findViewById(R.id.forum_activity_name);
            activeStateTv  = (TextView) itemView.findViewById(R.id.active_state);
            lastMessageTextTv  = (TextView) itemView.findViewById(R.id.last_message_text);

        }

        private void setData(ForumListModel forumListModel) {

            this.forumListModel = forumListModel;

            //Loading profile picture
            Glide.with(context)
                    .load(forumListModel.getProfilePhoto())
                    .into(profilePictureCv);

            //loading activity name
            activityNameTv.setText(forumListModel.getActivityName());

            //loading active state
            activeStateTv.setText(forumListModel.getActiveState());

            //loading last message info

            if (forumListModel.getLastMessageText() != null) { // when last message is not null

                lastMessageProfilePictureCv.setVisibility(View.VISIBLE);
                lastMessageTextTv.setVisibility(View.VISIBLE);

                lastMessageTextTv.setText(forumListModel.getLastMessageText());
                Glide.with(context)
                        .load(forumListModel.getLastMessageProfilePicture())
                        .into(lastMessageProfilePictureCv);

            }
            else  { // when last message is  null

                lastMessageProfilePictureCv.setVisibility(View.GONE);
                lastMessageTextTv.setVisibility(View.GONE);

            }


        }
    }

    /**
     * HeaderViewHolder for header only
     */
    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
