package ru.hse.roguelike.model.Characters;

import java.util.Random;

public class Obstacle extends GameCharacter {
    int destroyBonus = 1;

    public Obstacle() {
        super(CharacterType.OBSTACLE);
    }

    public Obstacle(int destroyBonus) {
        super(CharacterType.OBSTACLE);
        Random rand = new Random();
        this.destroyBonus = rand.nextInt(2);
    }

    public int getDestroyBonus() {
        return destroyBonus;
    }
}
