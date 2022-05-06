package ru.hse.roguelike.model.character;

import java.util.Random;

/**
 * Represents obstacle.
 **/
public class Obstacle extends GameCharacter {
    private final int destroyBonus;

    /**
     * Creates new Obstacle instance.
     * Calls parent constructor.
     * Initialises obstacle type randomly.
     **/
    public Obstacle() {
        super(CharacterType.OBSTACLE);
        Random rand = new Random();
        this.destroyBonus = rand.nextInt(2);
    }

    /**
     * Creates new Obstacle instance.
     * Calls parent constructor.
     * Initialises obstacle type with the given value.
     *
     * @param destroyBonus destroy bonus
     **/
    public Obstacle(int destroyBonus) {
        super(CharacterType.OBSTACLE);
        this.destroyBonus = destroyBonus;
    }

    /**
     * Returns destroy bonus.
     *
     * @return destroy bonus
     **/
    public int getDestroyBonus() {
        return destroyBonus;
    }
}
