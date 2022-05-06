package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.Screen;

public class MainMenuExitCommand extends MicroCommand {
    @Override
    public Screen execute() {
        return Screen.NONE;
    }
}
