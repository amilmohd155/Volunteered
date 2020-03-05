/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/22/20 9:57 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/22/20 9:57 PM
 *
 */

package com.volunteerx.app.profile.repository;

import androidx.lifecycle.MutableLiveData;

import com.volunteerx.app.firebase.FirebaseMethods;
import com.volunteerx.app.models.User;

import java.util.ArrayList;

public class UserRepository {

    private static UserRepository instance;
    private FirebaseMethods methods = new FirebaseMethods();

    public static UserRepository getInstance() {
        if (instance == null)
            instance = new UserRepository();
        return instance;
    }

    public MutableLiveData<User> getCurrentUserData() {

//        final MutableLiveData<User> user = new MutableLiveData<>();

           MutableLiveData<User> user = methods.getCurrentUserData();



//        Date timeStamp,
//        List<Integer> userCharacter,
//        String bio,
//        String dateOfBirth,
//        String email,
//        String name,
//        String photoUrl,
//        String phone,
//        String username,
//        String website,
//        int activityCount,
//        int followersCount,
//        int followingCount

//        user.setValue(new User(null,
//                null,
//                "john doe",
//                "john doe",
//                "john doe",
//                "john doe",
//                "john doe",
//                "john doe",
//                "john doe",
//                "john doe",
//                10,
//                100,
//                200));

        return user;

    }

}
