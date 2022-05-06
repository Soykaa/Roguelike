package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.character.mob.strategy.MobStrategy;

/**
 * Interface for mob state, which defines its behaviour.
 **/
public interface MobState {
    /**
     * Returns mob strategy.
     *
     * @return mob strategy
     **/
    MobStrategy getStrategy();

    /**
     * Makes next mob move.
     *
     * @param mobCoordinates    current mob coordinates
     * @param playerCoordinates current player coordinates
     * @return new mob coordinates
     **/
    Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates);
}
