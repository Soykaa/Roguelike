package ru.hse.roguelike.model.Characters;

/**
 * Represents game different types of game objects(characters).
 **/
public abstract class GameCharacter {
    private CharacterType characterType;

    /**
     * Creates new GameCharacter instance.
     * Initialises character type with the given value.
     *
     * @param characterType character type
     **/
    public GameCharacter(CharacterType characterType) {
        this.characterType = characterType;
    }

    /**
     * Returns character type.
     *
     * @return character type
     **/
    public CharacterType getCharacterType() {
        return characterType;
    }

    /**
     * Sets token type same as the given value.
     *
     * @param characterType character type
     **/
    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
    }
}
