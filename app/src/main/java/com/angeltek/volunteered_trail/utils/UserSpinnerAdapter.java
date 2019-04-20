package com.angeltek.volunteered_trail.utils;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.user.UserActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {


    private Context context;
    private ArrayList<String> text;
    private ArrayList<Integer> drawable;
    private int viewResource;
    private int dropViewResource;

    public UserSpinnerAdapter(Context context, int viewResource,int dropViewResource, ArrayList<String> text, ArrayList<Integer> drawable) {
        this.context = context;
        this.viewResource = viewResource;
        this.dropViewResource = dropViewResource;
        this.text = text;
        this.drawable = drawable;
    }

    @Override
    public int getCount() {
        return drawable.size();
    }

    @Override
    public Object getItem(int position) {
        return drawable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = View.inflate(context, viewResource,null);
        TextView username  = (TextView) view.findViewById(R.id.list_item);

        username.setText(text.get(position));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, dropViewResource, null);
        TextView username = (TextView) view.findViewById(R.id.dropdown_text);
        CircleImageView profilePicture = (CircleImageView) view.findViewById(R.id.dropdown_profilePhoto);

        username.setText(text.get(position));
        profilePicture.setImageDrawable(context.getDrawable(drawable.get(position)));

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        int widthDP = displayMetrics.widthPixels;

        view.setMinimumWidth(widthDP);

        //selected dropdown Styling
        if (UserActivity.selectedPosition == position) {

//            username.setTextColor(context.getResources().getColor(R.color.colorWhite, context.getTheme()));
//            username.setTypeface(username.getTypeface(), Typeface.BOLD);
            view.setBackgroundColor(context.getColor(R.color.colorDBDEFE));

        }

        return view;
    }
}
