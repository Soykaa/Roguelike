package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

public class MainMenuUpCommand extends MicroCommand {
    private final ItemHolder itemHolder;
    private final MainScreenView mainScreenView;

    public MainMenuUpCommand(ItemHolder itemHolder, MainScreenView mainScreenView) {
        this.itemHolder = itemHolder;
        this.mainScreenView = mainScreenView;
    }

    @Override
    public Screen execute() {
        itemHolder.moveUp();
        mainScreenView.setSelectedItem(itemHolder.getCurrentItem());
        return Screen.MAIN_MENU;
    }
}
