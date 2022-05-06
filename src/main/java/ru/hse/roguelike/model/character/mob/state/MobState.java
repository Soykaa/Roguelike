package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.character.mob.strategy.MobStrategy;

/**
 * Represents mob state, which defines its behaviour.
 **/
public interface MobState {
    MobStrategy getStrategy();

    Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates);
}
