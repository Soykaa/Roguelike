package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;

/**
 * Represents "show rules" option in main menu.
 **/
public class MainMenuShowRulesCommand implements MicroCommand {
    private final GameRulesScreenView gameRulesView;

    /**
     * Creates new MainMenuShowRulesCommand instance.
     *
     * @param gameRulesView game rules view
     **/
    public MainMenuShowRulesCommand(GameRulesScreenView gameRulesView) {
        this.gameRulesView = gameRulesView;
    }

    /**
     * Executes command.
     *
     * @return game rules screen
     **/
    @Override
    public Screen execute() {
        gameRulesView.showGameRules();
        return Screen.GAME_RULES;
    }
}
