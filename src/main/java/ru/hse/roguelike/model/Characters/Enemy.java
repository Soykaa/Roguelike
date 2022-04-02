package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;

public class Enemy extends Character {
    public Enemy() {
        super("Enemy");
    }

    Coordinates makeNextMove() {
        throw new UnsupportedOperationException();
    }

    void attack(Player player) {

    }
}
