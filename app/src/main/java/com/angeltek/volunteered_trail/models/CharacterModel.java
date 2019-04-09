package com.angeltek.volunteered_trail.models;

public class CharacterModel {

    private String characterName;
    private int characterColor;
    private int characterIcon;
    private boolean selectedCharacter;

    public CharacterModel(String characterName, int characterColor, int characterIcon) {
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
