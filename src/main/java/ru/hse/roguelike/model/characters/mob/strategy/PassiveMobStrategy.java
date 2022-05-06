package ru.hse.roguelike.model.characters.mob.strategy;

import ru.hse.roguelike.model.Coordinates;

/**
 * Represents passive mob strategy.
 **/
public class PassiveMobStrategy extends MobStrategy {
    /**
     * Makes next move.
     *
     * @param mobCoordinates    mob coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return new Coordinates(0, 0);
    }
}
