/*
 * *
 *  * Created by Amil Muhammed Hamza on 3/1/20 10:31 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/1/20 10:31 AM
 *
 */

package com.volunteerx.app.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableField;
import androidx.databinding.adapters.ListenerUtil;
import androidx.databinding.adapters.TextViewBindingAdapter;

import com.bumptech.glide.request.RequestOptions;
import com.volunteerx.app.R;

public class BindingConfig {

    @BindingAdapter(value = {"android:beforeTextChanged", "android:onTextChanged",
            "android:afterTextChanged"},
            requireAll = false)
    public static void setTextWatcher(TextView view, final TextViewBindingAdapter.BeforeTextChanged before,
                                      final TextViewBindingAdapter.OnTextChanged on, final TextViewBindingAdapter.AfterTextChanged after) {
        TextWatcher newValue = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (on != null) {
                    on.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        TextWatcher oldValue = ListenerUtil.trackListener(view, newValue, R.id.textWatcher);
        if (oldValue != null) {
            view.removeTextChangedListener(oldValue);
        }
        view.addTextChangedListener(newValue);
    }

    //Load Profile Image
    @BindingAdapter({"loadProfileImage"})
    public static void loadImage(ImageView view, String imageUrl) {

        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.avatar_default)
                .error(R.drawable.avatar_default)
                .fallback(R.drawable.avatar_default)
                .apply(new RequestOptions().placeholder(R.drawable.avatar_default).error(R.drawable.avatar_default))
                .into(view);
    }


}
