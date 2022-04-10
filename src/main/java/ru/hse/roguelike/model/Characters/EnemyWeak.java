package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;

public class EnemyWeak extends Enemy {
    int stepCount = 0;
    int maxSteps;
    Coordinates shift;

    /**
     * Creates new EnemyWeak instance.
     * Calls parent constructor.
     * Initialises fields with the given values.
     *
     * @param maxSteps maximum number of steps in the current direction
     * @param shift    steps direction
     **/
    public EnemyWeak(int maxSteps, Coordinates shift) {
        super(CharacterType.ENEMY_WEAK);
        this.maxSteps = maxSteps;
        this.shift = shift;
    }

    /**
     * Makes next move.
     *
     * @return new coordinates
     **/
    @Override
    public Coordinates makeNextMove() {
        if (stepCount < maxSteps) {
            stepCount++;
            return shift;
        }
        stepCount = 0;
        shift.setX(-shift.getX());
        shift.setY(-shift.getY());
        return shift;
    }
}
