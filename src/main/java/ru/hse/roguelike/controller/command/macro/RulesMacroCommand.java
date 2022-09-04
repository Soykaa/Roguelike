package ru.hse.roguelike.controller.command.macro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

/**
 * Represents commands, connected with game rules screen displaying, with complicated logic.
 **/
public class RulesMacroCommand implements MacroCommand {
    private final MainScreenView mainScreenView;

    /**
     * Creates new RulesMacroCommand instance.
     *
     * @param mainScreenView main screen view
     **/
    public RulesMacroCommand(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
    }

    /**
     * Executes passed command.
     *
     * @param inputCommand passed command
     * @return corresponding screen (game rules or menu)
     **/
    @Override
    public Screen execute(InputCommand inputCommand) {
        if (inputCommand == InputCommand.ESCAPE) {
            mainScreenView.showMainScreen();
            return Screen.MAIN_MENU;
        }
        return Screen.GAME_RULES;
    }
}
