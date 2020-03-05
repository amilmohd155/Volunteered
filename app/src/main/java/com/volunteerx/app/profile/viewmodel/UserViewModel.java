/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/22/20 9:55 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/22/20 9:55 PM
 *
 */

package com.volunteerx.app.profile.viewmodel;

import android.text.Editable;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.request.RequestOptions;
import com.volunteerx.app.R;
import com.volunteerx.app.models.User;
import com.volunteerx.app.profile.repository.UserRepository;
import com.volunteerx.app.utils.CheckerClass;
import com.volunteerx.app.utils.GlideApp;

public class UserViewModel extends ViewModel{

    private static final String TAG = "UserViewModel";

    public MutableLiveData<Integer> progressValue = new MutableLiveData<>(View.VISIBLE);

    private MutableLiveData<User> user;
    private UserRepository userRepository;

    public void queryData() {

        progressValue.postValue(View.VISIBLE);

        userRepository = UserRepository.getInstance();
        user = userRepository.getCurrentUserData();

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {

        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.avatar_default)
                .error(R.drawable.avatar_default)
                .fallback(R.drawable.avatar_default)
                .apply(new RequestOptions().placeholder(R.drawable.avatar_default).error(R.drawable.avatar_default))
                .into(view);
    }

    public LiveData<User> getUser() {
        if (user == null)
            user = new MutableLiveData<>();
        return user;
    }

}
