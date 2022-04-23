package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Enemy;
import ru.hse.roguelike.model.Characters.strategies.AggressiveMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.CowardMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.PassiveMobStrategy;

public class RedEnemyFactory implements EnemyFactory {
    private final int visibility = 4;
    private final String color = "red";

    private final int attackStrength = 1;


    @Override
    public Enemy createAggressiveEnemy(int maxSteps, Coordinates shift) {
        return new Enemy(CharacterType.ENEMY_AGGRESSIVE, color, attackStrength,
                new AggressiveMobStrategy(visibility, maxSteps, shift));
    }

    @Override
    public Enemy createPassiveEnemy(int maxSteps, Coordinates shift) {
        return new Enemy(CharacterType.ENEMY_PASSIVE, color, attackStrength,
                new PassiveMobStrategy());
    }

    @Override
    public Enemy createCowardEnemy(int maxSteps, Coordinates shift) {
        return new Enemy(CharacterType.ENEMY_COWARD, color, attackStrength,
                new CowardMobStrategy(visibility, maxSteps, shift));
    }
}
