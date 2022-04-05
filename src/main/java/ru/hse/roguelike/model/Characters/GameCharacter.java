package ru.hse.roguelike.model.Characters;

public abstract class GameCharacter {
    private CharacterType characterType;

    public GameCharacter(CharacterType characterType) {
        this.characterType = characterType;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
    }
}
