/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/2/20 10:43 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/2/20 10:43 PM
 *
 */

package com.volunteerx.app.utils;

public interface IOonBackPressed {

    /**
     * If you return true the back press will not be taken into account, otherwise the activity will act naturally
     * @return true if your processing has priority if not false
     */
    boolean onBackPressed();

}
