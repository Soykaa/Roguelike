package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Random;

public class EnemyStrong extends Enemy{
    private final List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
                                                    new Coordinates(1, 0),
                                                    new Coordinates(0, -1),
                                                    new Coordinates(0, 1));
    Random rand = new Random();

    public EnemyStrong() {
        super(CharacterType.ENEMY_STRONG);
    }

    @Override
    public Coordinates makeNextMove() {
        return shifts.get(rand.nextInt(4));
    }
}
