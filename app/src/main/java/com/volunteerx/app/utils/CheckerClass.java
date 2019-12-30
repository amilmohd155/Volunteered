/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/27/19 4:30 PM
 *
 */

package com.volunteerx.app.utils;

import android.util.Patterns;

public class CheckerClass {

    public static boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(CharSequence phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

}
