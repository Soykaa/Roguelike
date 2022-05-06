package ru.hse.roguelike.model.characters.mob.state;

import ru.hse.roguelike.model.characters.mob.Mob;

/**
 * Represents "everything is fine" mob state.
 **/
public class OkMobState implements MobState {
    private int healthThreshold;
    /**
     * Changes mob state if it's necessary.
     *
     * @param mob mob
     **/
    @Override
    public void switchState(Mob mob) {
        throw new UnsupportedOperationException();
    }
}
