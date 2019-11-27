package com.volunteerx.app.api;

import com.volunteerx.app.models.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("register")
    Call<Response> createUser(
            @Field("username") String username,
            @Field("userData") String userData,
            @Field("password") String password,
            @Field("dataType") int dataType
    );
}
