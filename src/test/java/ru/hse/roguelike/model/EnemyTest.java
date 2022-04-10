package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.*;

public class EnemyTest {
    @Test
    public void attackTest() {
        var player = new Player(9);
        var enemyWeak = new EnemyWeak(5, new Coordinates(-1, 0));
        var enemyStrong = new EnemyStrong();

        Assertions.assertEquals(9, player.getLives());
        enemyWeak.attack(player);
        Assertions.assertEquals(7, player.getLives());
        enemyStrong.attack(player);
        Assertions.assertEquals(5, player.getLives());
        player.getBackpack().putItem(new Inventory(InventoryItem.PROTECTION));
        player.getBackpack().setNextActiveItem();
        enemyStrong.attack(player);
        Assertions.assertEquals(4, player.getLives());
        enemyWeak.attack(player);
        Assertions.assertEquals(3, player.getLives());
    }

    @Test
    public void makeNextMoveStrongTest() {
        var strongEnemy = new EnemyStrong();
        Coordinates coordinates = strongEnemy.makeNextMove();
        Assertions.assertTrue(coordinates.getX() == -1 || coordinates.getX() == 1 || coordinates.getX() == 0);
        Assertions.assertTrue(coordinates.getY() == -1 || coordinates.getY() == 1 || coordinates.getY() == 0);
    }

    @Test
    public void makeNextMoveWeak() {
        var weakEnemy = new EnemyWeak(0, new Coordinates(1, 1));
        Coordinates coordinates = weakEnemy.makeNextMove();
        Assertions.assertEquals(-1, coordinates.getX());
        Assertions.assertEquals(-1, coordinates.getY());
    }
}
