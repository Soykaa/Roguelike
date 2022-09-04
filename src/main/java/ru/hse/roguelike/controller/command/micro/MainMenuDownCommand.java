package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

/**
 * Represents "down" command in main menu.
 **/
public class MainMenuDownCommand implements MicroCommand {
    private final ItemHolder itemHolder;
    private final MainScreenView mainScreenView;

    /**
     * Creates new MainMenuDownCommand instance.
     *
     * @param itemHolder     menu item holder
     * @param mainScreenView main screen view
     **/
    public MainMenuDownCommand(ItemHolder itemHolder, MainScreenView mainScreenView) {
        this.itemHolder = itemHolder;
        this.mainScreenView = mainScreenView;
    }

    /**
     * Executes command.
     *
     * @return main menu screen
     **/
    @Override
    public Screen execute() {
        itemHolder.moveDown();
        mainScreenView.setSelectedItem(itemHolder.getCurrentItem());
        return Screen.MAIN_MENU;
    }
}
