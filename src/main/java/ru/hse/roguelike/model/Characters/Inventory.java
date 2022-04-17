package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.InventoryItem;

import java.util.Objects;

/**
 * Represents inventory.
 **/
public class Inventory extends GameCharacter {
    private final InventoryItem type;
    private boolean canUse = true;

    /**
     * Returns if the inventory can be used or not.
     *
     * @return true if it can be used, false otherwise
     **/
    public boolean canUse() {
        return canUse;
    }

    /**
     * Sets canUse flag.
     *
     * @param canUse canUse flag
     **/
    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    /**
     * Tells about equality of two inventory objects.
     *
     * @param o second inventory object
     * @return true if two inventory objects have the same type, false otherwise
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return type == inventory.type;
    }

    /**
     * Returns hash of an inventory object.
     *
     * @return inventory object hash
     **/
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
