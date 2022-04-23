package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Enemy;
import ru.hse.roguelike.model.Characters.decorator.ConfusedEnemyDecorator;
import ru.hse.roguelike.model.Characters.strategies.AggressiveMobStrategy;

public class ConfusedMobTest {
    private Coordinates addCoordinates(Coordinates coordinates, Coordinates shift) {
        return new Coordinates(coordinates.getX() + shift.getX(), coordinates.getY() + shift.getY());
    }

    @Test
    public void testConfusedMobPlayerReturnsToStrategyAfterConfusionIsOver() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_AGGRESSIVE, "color", 2,
                new AggressiveMobStrategy(3, 10, new Coordinates(1, 0)));
        ConfusedEnemyDecorator confusedAggressiveEnemy = new ConfusedEnemyDecorator(aggressiveEnemy, 1);
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(5, 5);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Coordinates newEnemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(newEnemyCoordinates.getX() - enemyCoordinates.getX() == 1 && newEnemyCoordinates.getY() - enemyCoordinates.getY() == 0);
        enemyCoordinates = newEnemyCoordinates;
        newEnemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(newEnemyCoordinates.getX() - enemyCoordinates.getX() == 1 && newEnemyCoordinates.getY() - enemyCoordinates.getY() == 0);
    }
}
