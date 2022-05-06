package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.character.mob.Mob;

/**
 * Represents mob state, which defines its behaviour.
 **/
public interface MobState {
    /**
     * Changes mob state if it's necessary.
     *
     * @param mob mob
     **/
    void switchState(Mob mob);
}
