package ru.hse.roguelike.model.Characters.decorator;

import ru.hse.roguelike.model.Characters.Enemy;
import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

/**
 * Confused enemy decorator.
 **/
public class ConfusedEnemyDecorator extends EnemyDecorator {
    private final int confusionTime;
    private int currentTime = 0;

    private final List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
            new Coordinates(1, 0),
            new Coordinates(0, -1),
            new Coordinates(0, 1));
    private final Random rand = new Random();

    /**
     * Creates new ConfusedEnemyDecorator instance.
     * Initialises enemy with the given value.
     *
     * @param enemy enemy
     **/
    public ConfusedEnemyDecorator(Enemy enemy) {
        this(enemy, 5);
    }

    /**
     * Creates new ConfusedEnemyDecorator instance.
     * Calls parent constructor.
     * Initialises enemy and confusionTime with the given values.
     *
     * @param enemy         enemy
     * @param confusionTime time of confusion
     **/
    public ConfusedEnemyDecorator(Enemy enemy, int confusionTime) {
        super(enemy);
        this.confusionTime = confusionTime;
    }

    /**
     * Makes next move.
     *
     * @param mobCoordinates    enemy coordinates
     * @param playerCoordinates player coordinates
     * @return new coordinates
     **/
    @Override
    public Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        if (currentTime == confusionTime) {
            return enemy.makeNextMove(mobCoordinates, playerCoordinates);
        }
        currentTime++;
        return shifts.get(rand.nextInt(4));
    }
}
