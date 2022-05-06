package ru.hse.roguelike.model.character.mob.factory;

import ru.hse.roguelike.model.character.CharacterType;
import ru.hse.roguelike.model.character.mob.Mob;
import ru.hse.roguelike.model.character.mob.strategy.AggressiveMobStrategy;
import ru.hse.roguelike.model.character.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.character.mob.strategy.PassiveMobStrategy;
import ru.hse.roguelike.model.Coordinates;

/**
 * Red mob factory.
 **/
public class RedMobFactory implements MobFactory {
    private final int visibility = 4;
    private final String color = "red";
    private final int attackStrength = 1;

    /**
     * Creates aggressive mob.
     *
     * @param shift    shift direction
     * @param maxSteps maximum number of steps in current direction
     **/
    @Override
    public Mob createAggressiveMob(int maxSteps, Coordinates shift) {
        return new Mob(CharacterType.MOB_AGGRESSIVE, color, attackStrength,
                new AggressiveMobStrategy(visibility, maxSteps, shift));
    }

    /**
     * Creates passive mob.
     **/
    @Override
    public Mob createPassiveMob() {
        return new Mob(CharacterType.MOB_PASSIVE, color, attackStrength,
                new PassiveMobStrategy());
    }

    /**
     * Creates coward mob.
     *
     * @param shift    shift direction
     * @param maxSteps maximum number of steps in current direction
     **/
    @Override
    public Mob createCowardMob(int maxSteps, Coordinates shift) {
        return new Mob(CharacterType.MOB_COWARD, color, attackStrength,
                new CowardMobStrategy(visibility, maxSteps, shift));
    }
}
