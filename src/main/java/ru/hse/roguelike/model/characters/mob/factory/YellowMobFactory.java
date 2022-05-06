package ru.hse.roguelike.model.characters.mob.factory;

import ru.hse.roguelike.model.characters.CharacterType;
import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.characters.mob.state.OkMobState;
import ru.hse.roguelike.model.characters.mob.strategy.AggressiveMobStrategy;
import ru.hse.roguelike.model.characters.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.characters.mob.strategy.PassiveMobStrategy;
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
                new OkMobState(new AggressiveMobStrategy(visibility, maxSteps, shift)));
    }

    /**
     * Creates passive mob.
     **/
    @Override
    public Mob createPassiveMob() {
        return new Mob(CharacterType.MOB_PASSIVE, color, attackStrength,
                new OkMobState(new PassiveMobStrategy()));
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
                new OkMobState(new CowardMobStrategy(visibility, maxSteps, shift)));
    }
}
