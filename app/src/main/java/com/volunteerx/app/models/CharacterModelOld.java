/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:01 PM
 *
 */

package com.volunteerx.app.models;

public class CharacterModelOld {

    private String characterName;
    private int characterColor;
    private int characterIcon;
    private boolean selectedCharacter;

    public CharacterModelOld(String characterName, int characterColor, int characterIcon) {
        this.characterName = characterName;
        this.characterColor = characterColor;
        this.characterIcon = characterIcon;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterColor() {
        return characterColor;
    }

    public void setCharacterColor(int characterColor) {
        this.characterColor = characterColor;
    }

    public int getCharacterIcon() {
        return characterIcon;
    }

    public void setCharacterIcon(int characterIcon) {
        this.characterIcon = characterIcon;
    }

    public boolean isSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(boolean selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}
