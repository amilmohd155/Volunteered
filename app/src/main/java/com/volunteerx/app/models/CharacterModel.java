/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:03 PM
 *
 */

package com.volunteerx.app.models;

public class CharacterModel {

    private int characterID;
    private int characterColorID;
    private int characterNameID;


    public CharacterModel(int characterID, int characterName, int characterImageID) {
        this.characterID = characterID;
        this.characterColorID = characterImageID;
        this.characterNameID = characterName;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getCharacterColorID() {
        return characterColorID;
    }

    public void setCharacterColorID(int characterColorID) {
        this.characterColorID = characterColorID;
    }

    public int getCharacterNameID() {
        return characterNameID;
    }

    public void setCharacterNameID(int characterNameID) {
        this.characterNameID = characterNameID;
    }
}