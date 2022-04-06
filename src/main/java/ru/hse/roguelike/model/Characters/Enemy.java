package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

public abstract class Enemy extends GameCharacter {
    public Enemy(CharacterType characterType) {
        super(characterType);
    }

    public abstract Coordinates makeNextMove();

    public void attack(Player player) {
        if (player.getBackpack().getActiveItem().getType() != InventoryItem.PROTECTION) {
            player.decreaseLives(1);
        }
    }
}
