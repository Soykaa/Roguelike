package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.character.CharacterType;
import ru.hse.roguelike.model.character.mob.Mob;
import ru.hse.roguelike.model.character.mob.state.OkMobState;
import ru.hse.roguelike.model.character.mob.strategy.AggressiveMobStrategy;
import ru.hse.roguelike.model.character.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.character.mob.strategy.PassiveMobStrategy;

public class MobStrategyTest {
    private Coordinates addCoordinates(Coordinates coordinates, Coordinates shift) {
        return new Coordinates(coordinates.getX() + shift.getX(), coordinates.getY() + shift.getY());
    }

    @Test
    public void testAggressiveMobPlayerIsClose() {
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_AGGRESSIVE, "color", 2,
                new OkMobState(new AggressiveMobStrategy(3, 10, new Coordinates(1, 0))));
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
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_AGGRESSIVE, "color", 2,
                new OkMobState(new AggressiveMobStrategy(3, 10, new Coordinates(0, 1))));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(4, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 2);
    }

    @Test
    public void testAggressiveMobStartChasingPlayerAfterSomeTime() {
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_AGGRESSIVE, "color", 2,
                new OkMobState(new AggressiveMobStrategy(3, 10, new Coordinates(1, 0))));
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
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_COWARD, "color", 2,
                new OkMobState(new CowardMobStrategy(3, 10, new Coordinates(1, 0))));
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
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_COWARD, "color", 2,
                new OkMobState(new CowardMobStrategy(3, 10, new Coordinates(1, 0))));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(1, 5);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == 0);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 2 && enemyCoordinates.getY() == 0);
    }

    @Test
    public void testCowardMobStopEscapingPlayerAfterSomeTime() {
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_COWARD, "color", 2,
                new OkMobState(new CowardMobStrategy(3, 10, new Coordinates(1, 0))));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(3, 3);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == -1);

        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 1 && enemyCoordinates.getY() == -1);
    }

    @Test
    public void testPassiveMobPlayerIsClose() {
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_PASSIVE, "color", 2,
                new OkMobState(new PassiveMobStrategy()));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(3, 1);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 0);
    }

    @Test
    public void testPassiveMobPlayerIsNotClose() {
        Mob aggressiveEnemy = new Mob(CharacterType.MOB_PASSIVE, "color", 2,
                new OkMobState(new PassiveMobStrategy()));
        Coordinates enemyCoordinates = new Coordinates(0, 0);
        Coordinates playerCoordinates = new Coordinates(4, 4);
        enemyCoordinates = addCoordinates(enemyCoordinates, aggressiveEnemy.makeNextMove(enemyCoordinates, playerCoordinates));
        Assertions.assertTrue(enemyCoordinates.getX() == 0 && enemyCoordinates.getY() == 0);
    }
}
