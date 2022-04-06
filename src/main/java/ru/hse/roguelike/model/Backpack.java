package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    private int activeItemNum = 0;
    private final List<Inventory> allItems = new ArrayList<>();

    public Backpack() {
        allItems.add(new Inventory(InventoryItem.DEFAULT));
    }

    public Inventory getActiveItem() {
        return allItems.get(activeItemNum);
    }

    public List<Inventory> getAllItems() {
        return allItems;
    }

    public void putItem(Inventory item) {
        allItems.add(item);
    }

    public void setNextActiveItem() {
        activeItemNum = (activeItemNum + 1) % allItems.size();
    }
}
