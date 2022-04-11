package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents enemies.
 **/
public abstract class Enemy extends GameCharacter {
    /**
     * Creates new Enemy instance.
     * Calls parent constructor.
     *
     * @param characterType enemy type
     **/
    public Enemy(CharacterType characterType) {
        super(characterType);
    }

    /**
     * Makes next move.
     *
     * @return new coordinates
     **/
    public abstract Coordinates makeNextMove();

    /**
     * Attacks player.
     *
     * @param player player
     **/
    public void attack(Player player) {
        if (player.getBackpack().getActiveItem().getType() != InventoryItem.PROTECTION) {
            player.decreaseLives(2);
        }
        if (player.getBackpack().getActiveItem().getType() == InventoryItem.PROTECTION) {
            player.decreaseLives(1);
        }
    }
}
