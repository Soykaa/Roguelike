package ru.hse.roguelike.model.character.mob.state;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.character.mob.Mob;
import ru.hse.roguelike.model.character.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.character.mob.strategy.MobStrategy;

import java.util.List;
import java.util.Random;

public class PanicMobState implements MobState {
    private final MobStrategy prevStrategy;
    private final MobStrategy currentStrategy;
    private int panicTime = 5;

    private final Mob mob;

    public MobStrategy getStrategy() {
        return currentStrategy;
    }

    public PanicMobState(Mob mob) {
        this.mob = mob;
        this.prevStrategy = mob.getState().getStrategy();
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
        mob.changeMobState(new OkMobState(prevStrategy));
    }

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
