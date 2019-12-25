package com.volunteerx.app.utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;

public class StatusColorClass {

    public final static boolean DARK_STATUS_BAR_ICON = true;
    public final static boolean LIGHT_STATUS_BAR_ICON = false;

    public static void setStatusColor(Activity activity, int color, boolean darkTexture) {

        Window window = activity.getWindow();

        if (darkTexture) {

            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }else window.getDecorView().setSystemUiVisibility(0);

        window.setStatusBarColor(color);

    }

}
