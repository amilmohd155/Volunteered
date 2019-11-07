package com.angeltek.volunteered_trail.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class Permissions {

    public static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    public static boolean checkPermissionArray(String[] permissions, Context context) {
        for (int i = 0; i < permissions.length; ++i) {
            String check = permissions[i];
            if (!checkPermissions(check, context)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkPermissions(String permission, Context context) {

        int permissionRequest = ActivityCompat.checkSelfPermission(context, permission);
        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        else {
            return true;
        }
    }

    public static void verifyPermission(String[] permissions, Activity activity, int RequestCode) {

        ActivityCompat.requestPermissions(
                activity,
                permissions,
                RequestCode
        );

    }

}
