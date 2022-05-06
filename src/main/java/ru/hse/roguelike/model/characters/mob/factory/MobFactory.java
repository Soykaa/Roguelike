package ru.hse.roguelike.model.characters.mob.factory;

import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.Coordinates;

/**
 * Interface for mob factory.
 **/
public interface MobFactory {
    /**
     * Creates aggressive mob.
     *
     * @param shift    shift direction
     * @param maxSteps maximum number of steps in current direction
     **/
    Mob createAggressiveMob(int maxSteps, Coordinates shift);

    /**
     * Creates passive mob.
     **/
    Mob createPassiveMob();

    /**
     * Creates coward mob.
     *
     * @param shift    shift direction
     * @param maxSteps maximum number of steps in current direction
     **/
    Mob createCowardMob(int maxSteps, Coordinates shift);
}
