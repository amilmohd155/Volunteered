/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/3/20 12:23 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/3/20 12:23 AM
 *
 */

package com.volunteerx.app.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.volunteerx.app.R;

public class FragmentLoadFunction {


    public static void replaceFragment(Fragment fragment, String fragmentName, FragmentManager fragmentManager) {

        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container,fragment , fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }else {
            throw new RuntimeException("FragmentManager is null");
        }

    }


}
