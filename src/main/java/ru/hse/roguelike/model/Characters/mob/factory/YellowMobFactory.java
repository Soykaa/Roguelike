package ru.hse.roguelike.model.Characters.mob.factory;

import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.mob.Mob;
import ru.hse.roguelike.model.Characters.mob.strategy.AggressiveMobStrategy;
import ru.hse.roguelike.model.Characters.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.Characters.mob.strategy.PassiveMobStrategy;
import ru.hse.roguelike.model.Coordinates;

/**
 * Yellow mob factory.
 **/
public class YellowMobFactory implements MobFactory {
    private final int visibility = 3;
    private final String color = "yellow";
    private final int attackStrength = 2;

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
