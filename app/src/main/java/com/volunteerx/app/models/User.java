/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/27/19 4:34 PM
 *
 */

package com.volunteerx.app.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.GlideApp;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;

public class User implements Parcelable {

    @ServerTimestamp
    private Date timeStamp;

    private List<Integer> userCharacter;

    private String bio;
    private String dateOfBirth;
    private String email;
    private String name;
    private String photoUrl;
    private String phone;
    private String username;
    private String website;

    private int activityCount;
    private int followersCount;
    private int followingCount;

    private String errorMessage;
    private boolean isNewUser;

    public User() {
    }

    public User(Date timeStamp,
                List<Integer> userCharacter,
                String bio,
                String dateOfBirth,
                String email,
                String name,
                String photoUrl,
                String phone,
                String username,
                String website,
                int activityCount,
                int followersCount,
                int followingCount,
                boolean isNewUser) {
        this.timeStamp = timeStamp;
        this.userCharacter = userCharacter;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.name = name;
        this.photoUrl = photoUrl;
        this.phone = phone;
        this.username = username;
        this.website = website;
        this.activityCount = activityCount;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.isNewUser = isNewUser;
    }

    public User(Parcel in) {
        in.readList(this.userCharacter, Integer.class.getClassLoader());
        this.photoUrl = in.readString();
        this.phone = in.readString();
        this.website = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.username = in.readString();

    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Integer> getUserCharacter() {
        return userCharacter;
    }

    public void setUserCharacter(List<Integer> userCharacter) {
        this.userCharacter = userCharacter;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    //Parcelling
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.userCharacter);
        parcel.writeString(this.photoUrl);
        parcel.writeString(this.phone);
        parcel.writeString(this.website);
        parcel.writeString(this.name);
        parcel.writeString(this.email);
        parcel.writeString(this.username);

    }

//    Date timeStamp,
//    List<Integer> userCharacter,
//    String bio,
//    String dateOfBirth,
//    String email,
//    String name,
//    String photoUrl,
//    String phone,
//    String username,
//    String website,
//    int activityCount,
//    int followersCount,
//    int followingCount

    @BindingAdapter({"imageLoad"})
    public static void loadImage(ImageView view, String imageUrl) {

        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.avatar_default)
                .error(R.drawable.avatar_default)
                .fallback(R.drawable.avatar_default)
                .apply(new RequestOptions().placeholder(R.drawable.avatar_default).error(R.drawable.avatar_default))
                .into(view);
    }

    @BindingAdapter("characterBackgroundColor")
    public static void setCharacterBackgroundColor(View view, int characterId) {
        view.setBackgroundTintList(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean nullElement() {
        return Stream.of(timeStamp,
                userCharacter,
                bio,
                dateOfBirth,
                email,
                name,
                photoUrl,
                phone,
                username,
                website,
                activityCount,
                followersCount,
                followingCount,
                isNewUser,
                errorMessage).allMatch(Objects::isNull);
    }

}
