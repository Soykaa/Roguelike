package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;

public class MainMenuShowRulesCommand implements MicroCommand {
    private final GameRulesScreenView gameRulesView;

    public MainMenuShowRulesCommand(GameRulesScreenView gameRulesView) {
        this.gameRulesView = gameRulesView;
    }

    @Override
    public Screen execute() {
        gameRulesView.showGameRules();
        return Screen.GAME_RULES;
    }
}
