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
    private int characterColor;
    private int characterNameID;
    private String characterName;
    private int characterIcon;

    /**
     *
     * @param characterID
     * @param characterNameID
     * @param characterImage
     */
    public CharacterModel(int characterID, int characterNameID, int characterImage) {
        this.characterID = characterID;
        this.characterColor = characterImage;
        this.characterNameID = characterNameID;
    }

    /**
     *
     * @param characterID
     * @param characterColor
     * @param characterName
     */
    public CharacterModel(int characterID, int characterColor, String characterName) {
        this.characterID = characterID;
        this.characterColor = characterColor;
        this.characterName = characterName;
    }

    /**
     *
     * @param characterID
     * @param characterColor
     * @param characterNameID
     * @param characterIcon
     */
    public CharacterModel(int characterID, int characterColor, int characterNameID, int characterIcon) {
        this.characterID = characterID;
        this.characterColor = characterColor;
        this.characterNameID = characterNameID;
        this.characterIcon = characterIcon;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getCharacterColor() {
        return characterColor;
    }

    public void setCharacterColor(int characterColor) {
        this.characterColor = characterColor;
    }

    public int getCharacterNameID() {
        return characterNameID;
    }

    public void setCharacterNameID(int characterNameID) {
        this.characterNameID = characterNameID;
    }

    public int getCharacterIcon() {
        return characterIcon;
    }

    public void setCharacterIcon(int characterIcon) {
        this.characterIcon = characterIcon;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
