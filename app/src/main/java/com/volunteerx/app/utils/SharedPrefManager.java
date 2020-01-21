/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 9:31 PM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {

    private static final String KEY_USER_ID = "keyUserId";
    private static final String KEY_WIZARD_COMPLETE = "KeyWizardComplete";
    private final int DEFAULT_INT_KEY_VALUE = -1;

    private Context context;

    public static final String SHARED_PREF_NAME = "VolunteerXAppShared";

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        return new SharedPrefManager(context);
    }

    /**
     * used to store login data (userId)
     * @return boolean value
     * @param userId
     */
    public boolean userLogin(int userId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userId);
        editor.apply();

        return true;
    }

    /**
     * used to check whether already loggedIn
     * @return isLoggedIn(Boolean)
     */
    public boolean isLoggedIn() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getInt(KEY_USER_ID, DEFAULT_INT_KEY_VALUE) != -1) return true;
        return false;

    }

    public int getUserId() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, DEFAULT_INT_KEY_VALUE);

    }

    public void wizardCompleted(boolean wc) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_WIZARD_COMPLETE, wc);
        editor.apply();
    }

    public boolean isWizardComplete() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_WIZARD_COMPLETE, false);
    }

}
