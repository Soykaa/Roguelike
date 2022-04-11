package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents inventory.
 **/
public class Inventory extends GameCharacter {
    private final InventoryItem type;

    /**
     * Creates new Inventory instance.
     * Calls parent constructor.
     * Initialises inventory type with the given value.
     *
     * @param type inventory type
     **/
    public Inventory(InventoryItem type) {
        super(CharacterType.INVENTORY);
        this.type = type;
    }

    /**
     * Returns inventory type.
     *
     * @return inventory type
     **/
    public InventoryItem getType() {
        return type;
    }
}
