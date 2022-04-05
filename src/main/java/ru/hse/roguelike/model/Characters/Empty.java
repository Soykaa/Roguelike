package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.CharacterType;

public class Empty extends GameCharacter {
    public Empty() {
        super(CharacterType.EMPTY);
    }
}
