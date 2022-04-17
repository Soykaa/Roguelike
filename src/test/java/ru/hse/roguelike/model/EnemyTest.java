package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.Characters.*;

public class EnemyTest {
    private Enemy createEnemy(CharacterType type) {
        return new Enemy(type, 3, 10, new Coordinates(-1, 0));
    }
    @Test
    public void simpleAttackTest() {
        var player = new Player(9);
        var enemyAggressive = createEnemy(CharacterType.ENEMY_AGGRESSIVE);
        var enemyPassive = createEnemy(CharacterType.ENEMY_PASSIVE);
        var enemyCoward = createEnemy(CharacterType.ENEMY_COWARD);

        Assertions.assertEquals(9, player.getLives());
        enemyAggressive.attack(player);
        Assertions.assertEquals(7, player.getLives());
        enemyPassive.attack(player);
        Assertions.assertEquals(5, player.getLives());
        enemyCoward.attack(player);
        Assertions.assertEquals(3, player.getLives());
    }

    @Test
    public void attackWithProtectionTest() {
        var player = new Player(9);
        var enemyAggressive = createEnemy(CharacterType.ENEMY_AGGRESSIVE);
        var enemyPassive = createEnemy(CharacterType.ENEMY_PASSIVE);
        var enemyCoward = createEnemy(CharacterType.ENEMY_COWARD);

        Assertions.assertEquals(9, player.getLives());
        player.getBackpack().putItem(new Inventory(InventoryItem.PROTECTION));
        player.getBackpack().setNextActiveItem();
        player.getBackpack().setNextActiveItem();
        enemyAggressive.attack(player);
        Assertions.assertEquals(8, player.getLives());
        enemyPassive.attack(player);
        Assertions.assertEquals(7, player.getLives());
        enemyCoward.attack(player);
        Assertions.assertEquals(6, player.getLives());
    }
}
