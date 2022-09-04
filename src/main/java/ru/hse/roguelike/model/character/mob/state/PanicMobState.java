package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.character.mob.Mob;
import ru.hse.roguelike.model.character.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.character.mob.strategy.MobStrategy;

import java.util.List;
import java.util.Random;

public class PanicMobState implements MobState {
    private final MobStrategy previousStrategy;
    private final MobStrategy currentStrategy;
    private int panicTime = 5;
    private final Mob mob;

    /**
     * Creates new PanicMobState instance.
     * Initialises mob with the given value and other fields randomly.
     *
     * @param mob mob
     **/
    public PanicMobState(Mob mob) {
        this.mob = mob;
        this.previousStrategy = mob.getState().getStrategy();
        Random rand = new Random();
        int maxStep = rand.nextInt(4) + 1;
        int randomShift = rand.nextInt(4);
        List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1));

        if (mob.getColor().equals("red")) {
            currentStrategy = new CowardMobStrategy(4, maxStep, shifts.get(randomShift));
        } else {
            currentStrategy = new CowardMobStrategy(3, maxStep, shifts.get(randomShift));
        }
    }

    private void switchState(Mob mob) {
        mob.changeMobState(new OkMobState(previousStrategy));
    }

    /**
     * Returns mob strategy.
     *
     * @return mob strategy
     **/
    @Override
    public MobStrategy getStrategy() {
        return currentStrategy;
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
        panicTime--;
        var coordinatesToRet = currentStrategy.makeNextMove(mobCoordinates, playerCoordinates);
        if (panicTime == 0) {
            switchState(mob);
            mob.increaseLives();
        }
        return coordinatesToRet;
    }
}
