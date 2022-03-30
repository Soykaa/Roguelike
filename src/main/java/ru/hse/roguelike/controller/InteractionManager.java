package ru.hse.roguelike.controller;

import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.GameRulesScreenView;

public class InteractionManager {
    private Screen screen = Screen.MAIN_MENU;
    private final MainScreenView mainScreenView;
    private final ItemHolder itemHolder = new ItemHolder();
    private final GameRulesScreenView gameRulesView;
    public boolean isRunning = true;

    public InteractionManager(MainScreenView mainScreenView, GameRulesScreenView gameRulesScreenView) {
        this.mainScreenView = mainScreenView;
        this.gameRulesView = gameRulesScreenView;
        this.mainScreenView.showMainScreen();
    }

    public void processCommand(InputCommand command) {
        switch (screen) {
            case MAIN_MENU -> processCommandMainMenu(command);
            case GAME_RULES -> processCommandRules(command);
        }
    }

    private void processCommandRules(InputCommand command) {
        if (command == InputCommand.ESCAPE) {
            mainScreenView.showMainScreen();
            screen = Screen.MAIN_MENU;
        }
    }

    private void processCommandMainMenu(InputCommand command) {
        switch (command) {
            case UP, DOWN -> mainScreenView.setSelectedItem(itemHolder.setSelectedItem(command));
            case ENTER -> {
                switch (itemHolder.getCurrentItem()) {
                    case EXIT -> isRunning = false;
                    case SHOW_RULES -> {
                        screen = Screen.GAME_RULES;
                        gameRulesView.showGameRules();
                    }
                    case START_GAME, START_GAME_FROM_FILE -> {
                        // TODO
                    }
                }
            }
        }
    }
}
