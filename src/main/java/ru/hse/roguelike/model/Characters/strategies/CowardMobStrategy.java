package ru.hse.roguelike.model.Characters.strategies;

import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

public class CowardMobStrategy extends MobStrategy {
    public CowardMobStrategy(int visibility, int maxSteps, Coordinates shift) {
        super(visibility, maxSteps, shift);
    }

    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        if (canNotSeePlayer(mobCoordinates, playerCoordinates)) {
            return makeNextMoveUsual();
        }
        return makeMoveFromPlayer(mobCoordinates, playerCoordinates);
    }

    protected Coordinates makeMoveFromPlayer(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        int deltaX = mobCoordinates.getX() -  playerCoordinates.getX();
        int deltaY = mobCoordinates.getY() -  playerCoordinates.getY();
        Random rand = new Random();

        if (deltaX== 0) {
            List<Coordinates> shifts = List.of(new Coordinates(0, (int)Math.signum(deltaY)),
                    new Coordinates(1, 0),
                    new Coordinates(-1, 0));
            return shifts.get(rand.nextInt(3));
        }

        if (deltaY == 0) {
            List<Coordinates> shifts = List.of(new Coordinates((int)Math.signum(deltaX), 0),
                    new Coordinates(0, -1),
                    new Coordinates(0, 1));
            return shifts.get(rand.nextInt(3));
        }

        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            return new Coordinates((int)Math.signum(deltaX), 0);
        }

        return new Coordinates(0, (int)Math.signum(deltaY));
    }
}
