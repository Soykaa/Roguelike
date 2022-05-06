package ru.hse.roguelike.controller.comands.macro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

public class RulesMacroCommand implements MacroCommand {
    private final MainScreenView mainScreenView;

    public RulesMacroCommand(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
    }


    @Override
    public Screen execute(InputCommand inputCommand) {
        if (inputCommand == InputCommand.ESCAPE) {
            mainScreenView.showMainScreen();
            return Screen.MAIN_MENU;
        }
        return Screen.GAME_RULES;
    }
}
