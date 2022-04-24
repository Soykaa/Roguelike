package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Enemy;

/**
 * Interface for enemy factory.
 **/
public interface EnemyFactory {
    /**
     * Creates aggressive enemy.
     *
     * @param shift    shift direction
     * @param maxSteps maximum number of steps in current direction
     **/
    Enemy createAggressiveEnemy(int maxSteps, Coordinates shift);

    /**
     * Creates passive enemy.
     **/
    Enemy createPassiveEnemy();

    /**
     * Creates coward enemy.
     *
     * @param shift    shift direction
     * @param maxSteps maximum number of steps in current direction
     **/
    Enemy createCowardEnemy(int maxSteps, Coordinates shift);
}
