package ru.hse.roguelike.model;

/**
 * Enum class for inventory item types.
 **/
public enum InventoryItem {
    /**
     * Items, which give player an opportunity to destroy obstacles.
     **/
    DESTROY,
    /**
     * Items, which reduce damage from the enemy attack.
     **/
    PROTECTION,
    /**
     * Item, which confuse enemy.
     **/
    CONFUSION,
    /**
     * Default item.
     **/
    DEFAULT
}
