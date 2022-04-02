package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Inventory;

import java.util.List;

public class Backpack {
    private Inventory activeItem = null;
    private List<Inventory> allItems;
}
