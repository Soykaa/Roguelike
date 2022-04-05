package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;

public class Enemy extends GameCharacter {
    public Enemy(CharacterType characterType) {
        super(characterType);
    }

    Coordinates makeNextMove() {
        throw new UnsupportedOperationException();
    }

    void attack(Player player) {

    }
}
