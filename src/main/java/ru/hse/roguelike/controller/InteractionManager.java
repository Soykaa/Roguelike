package ru.hse.roguelike.controller;

import java.io.IOException;

import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.view.GameScreenView;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.GameRulesScreenView;

public class InteractionManager {
    private Screen screen = Screen.MAIN_MENU;
    private final MainScreenView mainScreenView;
    private final ItemHolder itemHolder = new ItemHolder();
    private final GameRulesScreenView gameRulesView;
    private final GameScreenView gameView;
    private Game game = new Game("");
    public boolean isRunning = true;

    public InteractionManager(MainScreenView mainScreenView, GameRulesScreenView gameRulesScreenView, GameScreenView gameView) {
        this.mainScreenView = mainScreenView;
        this.gameRulesView = gameRulesScreenView;
        this.gameView = gameView;
        this.mainScreenView.showMainScreen();
    }

    public void processCommand(InputCommand command) throws IOException, InterruptedException {
        switch (screen) {
            case MAIN_MENU:
                processCommandMainMenu(command);
                break;
            case GAME_RULES:
                processCommandRules(command);
                break;
            case GAME:
                processCommandGame(command);
                break;
        }
    }

    private void processCommandRules(InputCommand command) {
        if (command == InputCommand.ESCAPE) {
            mainScreenView.showMainScreen();
            screen = Screen.MAIN_MENU;
        }
    }

    private void processCommandGame(InputCommand command) throws IOException {
        switch (command) {
            case UP:
                game.makeAction(Action.MOVE_UP);
                break;
            case DOWN:
                game.makeAction(Action.MOVE_DOWN);
                break;
            case LEFT:
                game.makeAction(Action.MOVE_LEFT);
                break;
            case RIGHT:
                game.makeAction(Action.MOVE_RIGHT);
                break;
        }
    }

    private void processCommandMainMenu(InputCommand command) throws IOException {
        switch (command) {
            case UP:
            case DOWN:
                mainScreenView.setSelectedItem(itemHolder.setSelectedItem(command));
                break;
            case ENTER:
                switch (itemHolder.getCurrentItem()) {
                    case EXIT:
                        isRunning = false;
                        break;
                    case SHOW_RULES:
                        screen = Screen.GAME_RULES;
                        gameRulesView.showGameRules();
                        break;
                    case START_GAME:
                    case START_GAME_FROM_FILE:
                        screen = Screen.GAME;
                        game.startGame(false, gameView);
                        break;
                }
        }
    }
}
