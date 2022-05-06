package ru.hse.roguelike.model.character.mob.strategy;

import ru.hse.roguelike.model.Coordinates;

/**
 * Represents mob strategy.
 **/
public abstract class MobStrategy {
    private int visibility = 0;
    private final int maxSteps;

    private int stepCount = 0;

    private final Coordinates shift;

    /**
     * Creates new MobStrategy instance.
     * Calls parent constructor.
     * Initialises visibility, maxSteps and shift with the given values.
     *
     * @param visibility mob visibility
     * @param maxSteps   maximum number of steps in shift direction
     * @param shift      shift direction
     **/
    public MobStrategy(int visibility, int maxSteps, Coordinates shift) {
        this.visibility = visibility;
        this.maxSteps = maxSteps;
        this.shift = shift;
    }

    /**
     * Creates new MobStrategy instance.
     * Initialises maxSteps with zero and shift with null.
     **/
    public MobStrategy() {
        maxSteps = 0;
        shift = null;
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    mob coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    public abstract Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates);

    protected Coordinates makeNextMoveUsual() {
        if (stepCount < maxSteps) {
            stepCount++;
            return shift;
        }
        stepCount = 0;
        shift.setX(-shift.getX());
        shift.setY(-shift.getY());
        return shift;
    }

    protected boolean canNotSeePlayer(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return Math.abs(mobCoordinates.getX() - playerCoordinates.getX()) > visibility ||
                Math.abs(mobCoordinates.getY() - playerCoordinates.getY()) > visibility;
    }
}
