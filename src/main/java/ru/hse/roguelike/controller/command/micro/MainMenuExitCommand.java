package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;

/**
 * Represents "exit" option in main menu.
 **/
public class MainMenuExitCommand implements MicroCommand {
    /**
     * Executes command.
     *
     * @return "none" screen
     **/
    @Override
    public Screen execute() {
        return Screen.NONE;
    }
}
