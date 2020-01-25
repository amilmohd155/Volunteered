/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:09 PM
 *
 */

package com.volunteerx.app.utils;


public interface ClickListener{
//    void onClick(View view, int position);
//    void onLongClick(View view,int position);

    void buttonClick(int type);
    boolean onLongClick(int args);

}