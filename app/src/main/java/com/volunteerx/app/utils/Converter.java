/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 7:46 PM
 *
 */

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
