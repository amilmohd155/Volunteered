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
