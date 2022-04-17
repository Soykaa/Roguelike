package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.InventoryItem;

import java.util.Objects;

/**
 * Represents inventory.
 **/
public class Inventory extends GameCharacter {
    private final InventoryItem type;

    private boolean canUse = true;

    public boolean canUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return type == inventory.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

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
