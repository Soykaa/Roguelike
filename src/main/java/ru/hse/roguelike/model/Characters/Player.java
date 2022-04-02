package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Backpack;

public class Player extends Character {
    private int lives;
    private int points;
    private final Backpack backpack = new Backpack();

    public Player(int lives, int points) {
        super("Player");
        this.lives = lives;
        this.points = points;
    }

    boolean canAttack() {
        throw new UnsupportedOperationException();
    }

    void decreaseLives() {

    }
}
