package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Enemy;
import ru.hse.roguelike.model.Characters.strategies.AggressiveMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.CowardMobStrategy;
import ru.hse.roguelike.model.Characters.strategies.PassiveMobStrategy;

public class MobStrategyTest {
    private Coordinates addCoordinates(Coordinates coordinates, Coordinates shift) {
        return new Coordinates(coordinates.getX() + shift.getX(), coordinates.getY() + shift.getY());
    }

    @Test
    public void testAggressiveMobPlayerIsClose() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_AGGRESSIVE, "color", 2,
                new AggressiveMobStrategy(3, 10, new Coordinates(1, 0)));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(3, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == 1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 2 && enemyCoordinates.getY() == 1);
    }

    @Test
    public void testAggressiveMobPlayerIsNotClose() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_AGGRESSIVE, "color", 2,
                new AggressiveMobStrategy(3, 10, new Coordinates(0, 1)));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(4, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 2);
    }

    @Test
    public void testAggressiveMobStartChasingPlayerAfterSomeTime() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_AGGRESSIVE, "color", 2,
                new AggressiveMobStrategy(3, 10, new Coordinates(1, 0)));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(4, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == 0);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == 1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 2 && enemyCoordinates.getY() == 1);
    }

    @Test
    public void testCowardMobPlayerIsClose() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_COWARD, "color", 2,
                new CowardMobStrategy(3, 10, new Coordinates(1, 0)));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(1, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == -1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == -1 && enemyCoordinates.getY() == -1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == -1 && enemyCoordinates.getY() == -2);
    }

    @Test
    public void testCowardMobPlayerIsNotClose() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_COWARD, "color", 2,
                new CowardMobStrategy(3, 10, new Coordinates(1, 0)));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(1, 5);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == 0);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 2 && enemyCoordinates.getY() == 0);
    }

    @Test
    public void testCowardMobStopEscapingPlayerAfterSomeTime() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_COWARD, "color", 2,
                new CowardMobStrategy(3, 10, new Coordinates(1, 0)));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(3, 3);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == -1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == -1);
    }

    @Test
    public void testPassiveMobPlayerIsClose() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_PASSIVE, "color", 2, new PassiveMobStrategy());
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(3, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 0);
    }

    @Test
    public void testPassiveMobPlayerIsNotClose() {
        Enemy aggressiveEnemy = new Enemy(CharacterType.ENEMY_PASSIVE, "color", 2, new PassiveMobStrategy());
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(4, 4);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 0);
    }
}
