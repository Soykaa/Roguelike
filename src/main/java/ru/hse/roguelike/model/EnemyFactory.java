package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Enemy;

public interface EnemyFactory {
    Enemy createAggressiveEnemy(int maxSteps, Coordinates shift);
    Enemy createPassiveEnemy(int maxSteps, Coordinates shift);
    Enemy createCowardEnemy(int maxSteps, Coordinates shift);
}
