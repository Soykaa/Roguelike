package ru.hse.roguelike.view.abstract_view;

import ru.hse.roguelike.controller.SelectedItem;

public interface MainScreenView {
    void showMainScreen();

    void setSelectedItem(SelectedItem item);
}
