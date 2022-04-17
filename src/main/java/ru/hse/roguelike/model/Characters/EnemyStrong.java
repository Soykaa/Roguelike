package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

/**
 * Represents strong enemy.
 **/
public class EnemyStrong extends Enemy {
    private final List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
            new Coordinates(1, 0),
            new Coordinates(0, -1),
            new Coordinates(0, 1));
    private final Random rand = new Random();

    /**
     * Creates new EnemyStrong instance.
     * Calls parent constructor.
     **/
    public EnemyStrong() {
        super(CharacterType.ENEMY_STRONG);
    }

    /**
     * Makes next move.
     *
     * @return new coordinates
     **/
    @Override
    public Coordinates makeNextMove() {
        return shifts.get(rand.nextInt(4));
    }
}
