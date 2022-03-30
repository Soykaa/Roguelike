package ru.hse.roguelike.view;

import ru.hse.roguelike.controller.SelectedItem;

public interface ApplicationView {
    void showInitialScreen();
    void setSelectedItem(SelectedItem item);
}
