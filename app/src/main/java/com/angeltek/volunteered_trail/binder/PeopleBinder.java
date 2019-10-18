package com.angeltek.volunteered_trail.binder;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.PeopleListModel;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class PeopleBinder extends ItemBinder<PeopleListModel, PeopleBinder.PeopleViewHolder> {

    private static final String TAG = "PeopleBinder";

    private Context context;

//    Constants
    private final int CUSTOM_SELECTION_TYPE = 0;
    private final int FOLLOWING_LIST = 1;
    private final int FOLLOWER_LIST = 2;

    public PeopleBinder(Context context) {
        this.context = context;
    }

    @Override
    public PeopleViewHolder createViewHolder(ViewGroup parent) {
        return new PeopleViewHolder(inflate(parent, R.layout.snippet_profile_selection_item));
    }

    @Override
    public void bindViewHolder(PeopleViewHolder holder, PeopleListModel item) {

        Glide.with(context)
                .load(item.getProfilePhoto())
                .into(holder.profilePicture);

        holder.TvFullName.setText(item.getFullName());
        holder.TvUserName.setText(item.getUsername());

        //todo complete hide btn and show
        switch (item.getTypeOfLayout()) {
            case CUSTOM_SELECTION_TYPE:
                break;
            case FOLLOWING_LIST:
                break;
            case FOLLOWER_LIST:
                break;
        }

    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof PeopleListModel;
    }

    public class PeopleViewHolder extends ItemViewHolder<PeopleListModel> {

        private CircleImageView profilePicture;
        private ImageView checkView;
        private Button followBtn;
        private TextView TvFullName, TvUserName;

        public PeopleViewHolder(View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.profile_picture);
            checkView = itemView.findViewById(R.id.check_view);
            followBtn = itemView.findViewById(R.id.follow_btn);
            TvFullName = itemView.findViewById(R.id.full_name);
            TvUserName = itemView.findViewById(R.id.user_name);

        }
    }
}
