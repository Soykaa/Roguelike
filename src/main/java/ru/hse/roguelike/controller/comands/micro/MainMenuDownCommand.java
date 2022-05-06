package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.ItemHolder;
import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

public class MainMenuDownCommand extends MicroCommand {
    private final ItemHolder itemHolder;
    private final MainScreenView mainScreenView;

    public MainMenuDownCommand(ItemHolder itemHolder, MainScreenView mainScreenView) {
        this.itemHolder = itemHolder;
        this.mainScreenView = mainScreenView;
    }

    @Override
    public Screen execute() {
        itemHolder.moveDown();
        mainScreenView.setSelectedItem(itemHolder.getCurrentItem());
        return Screen.MAIN_MENU;
    }
}
