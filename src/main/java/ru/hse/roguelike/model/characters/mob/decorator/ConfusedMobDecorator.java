package ru.hse.roguelike.model.characters.mob.decorator;

import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

/**
 * Confused mob decorator.
 **/
public class ConfusedMobDecorator extends MobDecorator {
    private final int confusionTime;
    private int currentTime = 0;

    private final List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
            new Coordinates(1, 0),
            new Coordinates(0, -1),
            new Coordinates(0, 1));
    private final Random rand = new Random();

    /**
     * Creates new ConfusedMobDecorator instance.
     * Initialises mob with the given value.
     *
     * @param mob mob
     **/
    public ConfusedMobDecorator(Mob mob) {
        this(mob, 5);
    }

    /**
     * Creates new ConfusedMobDecorator instance.
     * Calls parent constructor.
     * Initialises mob and confusionTime with the given values.
     *
     * @param mob           mob
     * @param confusionTime time of confusion
     **/
    public ConfusedMobDecorator(Mob mob, int confusionTime) {
        super(mob);
        this.confusionTime = confusionTime;
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    mob coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        if (currentTime == confusionTime) {
            return mob.makeNextMove(mobCoordinates, playerCoordinates);
        }
        currentTime++;
        return shifts.get(rand.nextInt(4));
    }
}
