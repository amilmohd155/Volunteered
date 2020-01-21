/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 9:09 PM
 *
 */

package com.volunteerx.app.api;

import com.volunteerx.app.api.model.PureErrorResponse;
import com.volunteerx.app.api.model.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("register")
    Call<Response> createUser(
            @Field("full-name") String fullName,
            @Field("user-data") String userData,
            @Field("password") String password,
            @Field("data-type") int dataType
    );

    @FormUrlEncoded
    @POST("login")
    Call<Response> userLogin(
            @Field("userData") String userData,
            @Field("password") String password,
            @Field("userDataType") int userDataType
    );

    @FormUrlEncoded
    @POST("isUsernameAvailable")
    Call<PureErrorResponse> usernameAvailability(
            @Field("user-name") String username
    );

    @FormUrlEncoded
    @POST("addUsername")
    Call<PureErrorResponse> addUsernameToDB(
            @Field("user-id") int userID,
            @Field("user-name") String username
    );

    @FormUrlEncoded
    @POST("setCharacter")
    Call<PureErrorResponse> setCharacter(
            @Field("user-id") int userID,
            @Field("character-set[]") ArrayList<Integer> characterSet
            );

    @FormUrlEncoded
    @POST("setDOB")
    Call<PureErrorResponse> setDOB(
            @Field("user-id") int user_id,
            @Field("dob") String strDateOfBirth
    );
}
