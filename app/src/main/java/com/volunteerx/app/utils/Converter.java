package com.volunteerx.app.utils;

import android.content.Context;
import android.util.TypedValue;

public class Converter {

    static public int pTd(Context context, int i) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                i,
                context.getResources().getDisplayMetrics()
        );
    }

}
