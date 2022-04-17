package ru.hse.roguelike.model.Characters.strategies;

import ru.hse.roguelike.model.Coordinates;

public class PassiveMobStrategy extends MobStrategy {

    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return new Coordinates(0, 0);
    }
}
