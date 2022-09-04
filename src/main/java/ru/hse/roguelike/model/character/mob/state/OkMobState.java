package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.character.mob.strategy.MobStrategy;

/**
 * Represents "everything is fine" mob state.
 **/
public class OkMobState implements MobState {
    private final MobStrategy strategy;

    /**
     * Creates new OkMobState instance.
     * Initialises strategy with the given value.
     *
     * @param strategy mob strategy
     **/
    public OkMobState(MobStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Returns mob strategy.
     *
     * @return mob strategy
     **/
    @Override
    public MobStrategy getStrategy() {
        return strategy;
    }

    /**
     * Makes next mob move.
     *
     * @param mobCoordinates    current mob coordinates
     * @param playerCoordinates current player coordinates
     * @return new mob coordinates
     **/
    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return strategy.makeNextMove(mobCoordinates, playerCoordinates);
    }
}
