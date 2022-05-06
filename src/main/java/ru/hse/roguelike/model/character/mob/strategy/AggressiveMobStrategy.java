package ru.hse.roguelike.model.character.mob.strategy;

import ru.hse.roguelike.model.Coordinates;

/**
 * Represents aggressive mob strategy.
 **/
public class AggressiveMobStrategy extends MobStrategy {
    /**
     * Creates new AggressiveMobStrategy instance.
     * Calls parent constructor.
     * Initialises visibility, maxSteps and shift with the given values.
     *
     * @param visibility mob visibility
     * @param maxSteps   maximum number of steps in shift direction
     * @param shift      shift direction
     **/
    public AggressiveMobStrategy(int visibility, int maxSteps, Coordinates shift) {
        super(visibility, maxSteps, shift);
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    mob coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        if (canNotSeePlayer(mobCoordinates, playerCoordinates)) {
            return makeNextMoveUsual();
        }
        var coordinates = makeMoveToPlayer(mobCoordinates, playerCoordinates);
        return new Coordinates(-coordinates.getX(), -coordinates.getY());
    }

    protected Coordinates makeMoveToPlayer(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        int deltaX = mobCoordinates.getX() - playerCoordinates.getX();
        int deltaY = mobCoordinates.getY() - playerCoordinates.getY();

        if (deltaX == 0) {
            return new Coordinates(0, (int) Math.signum(deltaY));
        }

        if (deltaY == 0) {
            return new Coordinates((int) Math.signum(deltaX), 0);
        }

        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            return new Coordinates((int) Math.signum(deltaX), 0);
        }

        return new Coordinates(0, (int) Math.signum(deltaY));
    }
}
