package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.InventoryItem;

public class Inventory extends GameCharacter {
    private final InventoryItem type;

    public Inventory(InventoryItem type) {
        super(CharacterType.INVENTORY_ATTACK);
        this.type = type;
    }

    public InventoryItem getType() {
        return type;
    }
}
