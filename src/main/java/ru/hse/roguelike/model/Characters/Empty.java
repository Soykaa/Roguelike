package ru.hse.roguelike.model.Characters;

/**
 * Represents empty cell of the game field.
 **/
public class Empty extends GameCharacter {
    /**
     * Creates new Empty instance.
     * Calls parent constructor.
     **/
    public Empty() {
        super(CharacterType.EMPTY);
    }
}
