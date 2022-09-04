package ru.hse.roguelike.controller;

import java.util.List;

/**
 * Holds main menu items.
 **/
public class ItemHolder {
    private final List<SelectedItem> items = List.of(SelectedItem.START_GAME,
            SelectedItem.START_GAME_FROM_FILE,
            SelectedItem.SHOW_RULES,
            SelectedItem.EXIT);
    private int currentItemNumber = 0;

    /**
     * Returns currently selected item.
     *
     * @return current item
     **/
    public SelectedItem getCurrentItem() {
        return items.get(currentItemNumber);
    }

    /**
     * Moves down to the next menu item.
     **/
    public void moveDown() {
        currentItemNumber = (currentItemNumber + 1) % items.size();
    }

    /**
     * Moves up to the next menu item.
     **/
    public void moveUp() {
        currentItemNumber = (items.size() + currentItemNumber - 1) % items.size();
    }
}
