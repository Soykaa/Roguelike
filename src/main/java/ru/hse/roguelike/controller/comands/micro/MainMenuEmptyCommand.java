package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.Screen;

public class MainMenuEmptyCommand implements MicroCommand {

    @Override
    public Screen execute() {
        return Screen.MAIN_MENU;
    }
}
