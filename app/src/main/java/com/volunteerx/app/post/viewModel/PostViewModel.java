/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/29/20 11:37 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/29/20 11:37 PM
 *
 */

package com.volunteerx.app.post.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.volunteerx.app.post.model.Post;
import com.volunteerx.app.post.repository.PostRepository;

//Todo complete the post insertion
public class PostViewModel extends ViewModel {


    private static final String TAG = "PostViewModel";

    private PostRepository repository;

    private MutableLiveData<Post> post;
    public MutableLiveData<String> postText = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPostReady = new MutableLiveData<>(false);

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().isEmpty()) isPostReady.postValue(true);
        else isPostReady.postValue(false);
    }

    public void submitPost() {


        repository = new PostRepository();

        repository.createPost(post);
    }


    public LiveData<Boolean> getIsPostReady() {
        return isPostReady;
    }

    public LiveData<Post> getPost() {
        if (post == null) post = new MutableLiveData<>();
        return post;
    }

}
