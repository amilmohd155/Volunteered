/*
 * *
 *  * Created by Amil Muhammed Hamza on 3/1/20 11:00 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/1/20 11:00 PM
 *
 */

package com.volunteerx.app.startup.viewModel;


import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.volunteerx.app.firebase.FirebaseMethods;
import com.volunteerx.app.models.User;
import com.volunteerx.app.startup.model.SigningUser;
import com.volunteerx.app.utils.CheckerClass;

public class SigningViewModel extends ViewModel {

    private static final String TAG = "SigningViewModel";

    public MutableLiveData<String> userData = new MutableLiveData<>();
    public MutableLiveData<String> fullname = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<SigningUser> userMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isReadyToContinue;
    private MutableLiveData<Task<AuthResult>> taskMutableLiveData;

    public void signUpButtonClick() {

//        userMutableLiveData.postValue(new SigningUser(userData.getValue(), fullname.getValue(), password.getValue()));

//        isReadyToContinue.postValue(true);

        signUpToFireBase();
    }

    public void signUpToFireBase() {
        FirebaseMethods methods = new FirebaseMethods();

        taskMutableLiveData = methods.createNewUser(new MutableLiveData<>(new SigningUser(userData.getValue(), fullname.getValue(), password.getValue())));

    }

    public LiveData<Boolean> getIsReadyToContinue() {
        if (isReadyToContinue == null) isReadyToContinue = new MutableLiveData<>(false);
        return isReadyToContinue;
    }

    public void setIsReadyToContinue(MutableLiveData<Boolean> isReadyToContinue) {
        this.isReadyToContinue = isReadyToContinue;
    }

    public LiveData<Task<AuthResult>> getTask() {
        if (taskMutableLiveData == null)
            taskMutableLiveData = new MutableLiveData<>();
        return taskMutableLiveData;
    }


    public boolean checkInputs(String userData, String fullname, String password) {
        if (userData == null || fullname == null || password == null)
            return false;

        return !userData.isEmpty()
                && !fullname.isEmpty()
                && !password.isEmpty()
                && (CheckerClass.isEmailValid(userData) || CheckerClass.isValidMobile(userData))
                && (TextUtils.getTrimmedLength(password) > 8);
    }

}
