package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.InventoryItem;

public class Inventory extends Character {
    private final InventoryItem type;

    public Inventory(InventoryItem type) {
        super("Inventory");
        this.type = type;
    }

    public InventoryItem getType() {
        return type;
    }
}
