package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.character.mob.Mob;

public class PanicMobState implements MobState {
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
