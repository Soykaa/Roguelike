package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.CharacterType;
import ru.hse.roguelike.model.Coordinates;

public class Enemy extends GameCharacter {
    public Enemy() {
        super(CharacterType.ENEMY_STRONG);
    }

    Coordinates makeNextMove() {
        throw new UnsupportedOperationException();
    }

    void attack(Player player) {

    }
}
