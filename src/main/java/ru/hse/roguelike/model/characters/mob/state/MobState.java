package ru.hse.roguelike.model.characters.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.characters.mob.strategy.MobStrategy;

/**
 * Represents mob state, which defines its behaviour.
 **/
public interface MobState {
    MobStrategy getStrategy();

    Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates);
}
