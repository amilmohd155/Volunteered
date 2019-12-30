/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 8:33 PM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceCheckerClass {

    public static void savePrefsData(Context context, String prefName, String key) {

        SharedPreferences preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, true).apply();

    }

    public static boolean restorePrefsData(Context context, String prefName, String key) {

        SharedPreferences preferences = context.getSharedPreferences(prefName, MODE_PRIVATE);

        return preferences.getBoolean(key, false);

    }

}
