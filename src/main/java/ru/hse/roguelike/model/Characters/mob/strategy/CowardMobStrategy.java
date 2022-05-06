package ru.hse.roguelike.model.Characters.mob.strategy;

import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

/**
 * Represents coward mob strategy.
 **/
public class CowardMobStrategy extends MobStrategy {
    /**
     * Creates new CowardMobStrategy instance.
     * Calls parent constructor.
     * Initialises visibility, maxSteps and shift with the given values.
     *
     * @param visibility mob visibility
     * @param maxSteps   maximum number of steps in shift direction
     * @param shift      shift direction
     **/
    public CowardMobStrategy(int visibility, int maxSteps, Coordinates shift) {
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
        return makeMoveFromPlayer(mobCoordinates, playerCoordinates);
    }

    protected Coordinates makeMoveFromPlayer(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        int deltaX = mobCoordinates.getX() - playerCoordinates.getX();
        int deltaY = mobCoordinates.getY() - playerCoordinates.getY();
        Random rand = new Random();

        if (deltaX == 0) {
            List<Coordinates> shifts = List.of(new Coordinates(0, (int) Math.signum(deltaY)),
                    new Coordinates(1, 0),
                    new Coordinates(-1, 0));
            return shifts.get(rand.nextInt(3));
        }

        if (deltaY == 0) {
            List<Coordinates> shifts = List.of(new Coordinates((int) Math.signum(deltaX), 0),
                    new Coordinates(0, -1),
                    new Coordinates(0, 1));
            return shifts.get(rand.nextInt(3));
        }

        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            return new Coordinates((int) Math.signum(deltaX), 0);
        }

        return new Coordinates(0, (int) Math.signum(deltaY));
    }
}
