package ru.hse.roguelike.controller;

import ru.hse.roguelike.controller.input.InputCommand;

import java.util.List;

public class ItemHolder {
    private final List<SelectedItem> items = List.of(SelectedItem.START_GAME,
                                       SelectedItem.START_GAME_FROM_FILE,
                                       SelectedItem.SHOW_RULES,
                                       SelectedItem.EXIT);
    private int currentItemNumber = 0;

    public SelectedItem getCurrentItem() {
        return items.get(currentItemNumber);
    }

    public SelectedItem setSelectedItem(InputCommand command) {
        switch (command) {
            case DOWN:
                currentItemNumber = (currentItemNumber + 1) % items.size();
                break;
            case UP:
                currentItemNumber = (items.size() + currentItemNumber - 1) % items.size();
                break;
        }
        return items.get(currentItemNumber);
    }
}
