package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents player backpack.
 **/
public class Backpack {
    private int activeItemNum = 0;
    private final List<Inventory> allItems = new ArrayList<>();

    /**
     * Creates new Backpack instance with default item.
     **/
    public Backpack() {
        allItems.add(new Inventory(InventoryItem.DEFAULT));
        allItems.add(new Inventory(InventoryItem.CONFUSION));
    }

    /**
     * Returns current active item.
     *
     * @return active item
     **/
    public Inventory getActiveItem() {
        return allItems.get(activeItemNum);
    }

    /**
     * Returns all items in a backpack.
     *
     * @return items as list
     **/
    public List<Inventory> getAllItems() {
        return allItems;
    }

    /**
     * Puts item in a backpack.
     *
     * @param item item to put
     **/
    public void putItem(Inventory item) {
        allItems.add(item);
    }

    /**
     * Sets next item (in the list) as active.
     **/
    public void setNextActiveItem() {
        activeItemNum = (activeItemNum + 1) % allItems.size();
        while (!allItems.get(activeItemNum).canUse()) {
            activeItemNum = (activeItemNum + 1) % allItems.size();
        }
    }

    /**
     * Clear backpack.
     **/
    public void clear() {
        allItems.clear();
        activeItemNum = 0;
        allItems.add(new Inventory(InventoryItem.DEFAULT));
        allItems.add(new Inventory(InventoryItem.CONFUSION));
    }

    /**
     * Makes inventory item unusable.
     *
     * @param inventoryItem inventory item
     **/
    public void makeUnusable(InventoryItem inventoryItem) {
        for (var inventory : allItems) {
            if (inventory.getType() == inventoryItem) {
                inventory.setCanUse(false);
            }
        }
        if (!allItems.get(activeItemNum).canUse()) {
            setNextActiveItem();
        }
    }

    /**
     * Makes inventory item usable.
     *
     * @param inventoryItem inventory item
     **/
    public void makeUsable(InventoryItem inventoryItem) {
        for (var inventory : allItems) {
            if (inventory.getType() == inventoryItem) {
                inventory.setCanUse(true);
            }
        }
    }
}
