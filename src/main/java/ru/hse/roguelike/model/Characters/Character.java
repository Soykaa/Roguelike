package ru.hse.roguelike.model.Characters;

public abstract class Character {
    private String characterName;

    public Character(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
