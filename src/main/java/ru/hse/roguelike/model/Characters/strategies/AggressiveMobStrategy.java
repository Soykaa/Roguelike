package ru.hse.roguelike.model.Characters.strategies;

import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

public class AggressiveMobStrategy extends MobStrategy {

    public AggressiveMobStrategy(int visibility, int maxSteps, Coordinates shift) {
        super(visibility, maxSteps, shift);
    }

    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        if (canNotSeePlayer(mobCoordinates, playerCoordinates)) {
            return makeNextMoveUsual();
        }
        var coordinates = makeMoveToPlayer(mobCoordinates, playerCoordinates);
        return new Coordinates(-coordinates.getX(), -coordinates.getY());
    }

    protected Coordinates makeMoveToPlayer(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        int deltaX = mobCoordinates.getX() -  playerCoordinates.getX();
        int deltaY = mobCoordinates.getY() -  playerCoordinates.getY();

        if (deltaX== 0) {
            return new Coordinates(0, (int)Math.signum(deltaY));
        }

        if (deltaY == 0) {
            return new Coordinates((int)Math.signum(deltaX), 0);
        }

        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            return new Coordinates((int)Math.signum(deltaX), 0);
        }

        return new Coordinates(0, (int)Math.signum(deltaY));
    }


}
