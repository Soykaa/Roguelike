package ru.hse.roguelike.view.abstract_view;

import ru.hse.roguelike.controller.SelectedItem;

/**
 * Interface for main screen view.
 **/
public interface MainScreenView {
    /**
     * Shows main screen.
     **/
    void showMainScreen();

    /**
     * Sets selected menu item.
     *
     * @param item selected item
     **/
    void setSelectedItem(SelectedItem item);
}
