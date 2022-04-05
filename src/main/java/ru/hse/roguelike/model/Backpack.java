package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    private Inventory activeItem = null;
    private List<Inventory> allItems = new ArrayList<>();

    public Inventory getActiveItem() {
        return activeItem;
    }

    public List<Inventory> getAllItems() {
        return allItems;
    }

    public void setActiveItem(Inventory activeItem) {
        this.activeItem = activeItem;
    }

    public void addItem(Inventory item) {
        allItems.add(item);
    }

    public void removeAllItems() {
        allItems.clear();
    }
}
