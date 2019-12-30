/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/26/19 3:16 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/26/19 3:16 PM
 *
 */

package com.volunteerx.app.utils;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class UtilClass {

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException e) {
            e.getStackTrace();
        }
    }

}
