/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 6:23 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 6:23 PM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.volunteerx.app.R;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CharacterHelper {

    private ArrayList<Integer> characterColor = new ArrayList<>();
    private int characterId;
    private Context context;

    public CharacterHelper() {
    }

    public CharacterHelper(Context context) {
        this.context = context;

        setCharacterColor();

    }

    public CharacterHelper(Context context, int characterId) {
        this.characterId = characterId;
        this.context = context;

        setCharacterColor();

    }

    private void setCharacterColor() {

        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.characterColor);

        for (int i =0; i < typedArray.length(); ++i) {
            characterColor.add(typedArray.getResourceId(i, 0));
        }

        typedArray.recycle();

    }

    public ArrayList<Integer> getCharacterColor(ArrayList<Integer> characterIdList) {

        ArrayList<Integer> characterColorList = new ArrayList<>();

        for (int characterId : characterIdList) {
            characterColorList.add(context.getColor(characterColor.get(characterId)));
        }

        return characterColorList;
    }

    public int getCharacterColor(int characterId) {
        return context.getColor(characterColor.get(characterId));
    }

    public String getCharacterName(int characterId) {
        return context.getResources().getStringArray(R.array.characterName)[characterId];
    }

    public String getCharacterNameID(int characterId) {
        return context.getResources().getStringArray(R.array.characterName)[characterId];
    }

    public void recycle() {

        new Handler(Looper.getMainLooper()).postDelayed(() -> characterColor = null, 5000);


    }

    public static String getCharacterName(View view, int characterId) {
        return view.getResources().getStringArray(R.array.characterName)[characterId];
    }

}
