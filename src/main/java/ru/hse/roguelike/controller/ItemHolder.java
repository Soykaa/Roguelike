package ru.hse.roguelike.controller;

import ru.hse.roguelike.controller.input.InputCommand;

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

    public void moveDown() {
        currentItemNumber = (currentItemNumber + 1) % items.size();
    }

    public void moveUp() {
        currentItemNumber = (items.size() + currentItemNumber - 1) % items.size();
    }
}
