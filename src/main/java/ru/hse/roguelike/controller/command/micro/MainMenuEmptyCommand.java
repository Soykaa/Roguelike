package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;

/**
 * Represents command, that doesn't affect main menu screen.
 **/
public class MainMenuEmptyCommand implements MicroCommand {
    /**
     * Executes command.
     *
     * @return main menu screen
     **/
    @Override
    public Screen execute() {
        return Screen.MAIN_MENU;
    }
}
