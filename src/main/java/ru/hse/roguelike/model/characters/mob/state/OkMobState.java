package ru.hse.roguelike.model.characters.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.characters.mob.strategy.MobStrategy;

/**
 * Represents "everything is fine" mob state.
 **/
public class OkMobState implements MobState {
    private final MobStrategy strategy;

    public OkMobState(MobStrategy strategy) {
        this.strategy = strategy;
    }

    public MobStrategy getStrategy() {
        return strategy;
    }

    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return strategy.makeNextMove(mobCoordinates, playerCoordinates);
    }

}
