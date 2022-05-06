package ru.hse.roguelike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hse.roguelike.model.characters.*;
import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.characters.mob.strategy.AggressiveMobStrategy;
import ru.hse.roguelike.model.characters.mob.strategy.CowardMobStrategy;
import ru.hse.roguelike.model.characters.mob.strategy.PassiveMobStrategy;

public class MobTest {
    private Mob createMob(CharacterType type) {
        switch (type) {
            case MOB_AGGRESSIVE:
                return new Mob(type, "color", 2,
                        new AggressiveMobStrategy(3, 10, new Coordinates(-1, 0)));
            case MOB_COWARD:
                return new Mob(type, "color", 2,
                        new CowardMobStrategy(3, 10, new Coordinates(-1, 0)));
            default:
                return new Mob(type, "color", 2, new PassiveMobStrategy());
        }
    }

    @Test
    public void simpleAttackTest() {
        var player = new Player(9, 0);
        var mobAggressive = createMob(CharacterType.MOB_AGGRESSIVE);
        var mobPassive = createMob(CharacterType.MOB_PASSIVE);
        var mobCoward = createMob(CharacterType.MOB_COWARD);

        Assertions.assertEquals(9, player.getLives());
        mobAggressive.attack(player);
        Assertions.assertEquals(7, player.getLives());
        mobPassive.attack(player);
        Assertions.assertEquals(5, player.getLives());
        mobCoward.attack(player);
        Assertions.assertEquals(3, player.getLives());
    }

    @Test
    public void attackWithProtectionTest() {
        var player = new Player(9, 0);
        var mobAggressive = createMob(CharacterType.MOB_AGGRESSIVE);
        var mobPassive = createMob(CharacterType.MOB_PASSIVE);
        var mobCoward = createMob(CharacterType.MOB_COWARD);

        Assertions.assertEquals(9, player.getLives());
        player.getBackpack().putItem(new Inventory(InventoryItem.PROTECTION));
        player.getBackpack().setNextActiveItem();
        player.getBackpack().setNextActiveItem();
        mobAggressive.attack(player);
        Assertions.assertEquals(8, player.getLives());
        mobPassive.attack(player);
        Assertions.assertEquals(7, player.getLives());
        mobCoward.attack(player);
        Assertions.assertEquals(6, player.getLives());
    }
}
