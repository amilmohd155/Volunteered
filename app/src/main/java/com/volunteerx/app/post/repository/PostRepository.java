/*
 * *
 *  * Created by Amil Muhammed Hamza on 3/1/20 11:41 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/1/20 11:41 AM
 *
 */

package com.volunteerx.app.post.repository;

import androidx.lifecycle.MutableLiveData;

import com.volunteerx.app.firebase.FirebaseMethods;
import com.volunteerx.app.models.PostModel;
import com.volunteerx.app.post.model.Post;

public class PostRepository {

    private static PostRepository instance;
    private FirebaseMethods methods;

    public static PostRepository getInstance() {
        if (instance == null) instance = new PostRepository();
        return instance;
    }

    public void createPost(MutableLiveData<Post> postModel) {

        methods.createNewPost(postModel);
    }

}
