package com.volunteerx.app.api;

import com.volunteerx.app.models.PostModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/")
    Call<PostModel> getInfo();

}
