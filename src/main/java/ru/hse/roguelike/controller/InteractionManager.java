package ru.hse.roguelike.controller;

import java.io.IOException;

import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.Result;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.GameRulesScreenView;

public class InteractionManager {
    private Screen screen = Screen.MAIN_MENU;
    private final MainScreenView mainScreenView;
    private final ItemHolder itemHolder = new ItemHolder();
    private final GameRulesScreenView gameRulesView;
    private final Game game = new Game("");
    public boolean isRunning = true;
    private final Terminal terminal;

    public InteractionManager(MainScreenView mainScreenView, GameRulesScreenView gameRulesScreenView, Terminal terminal) {
        this.mainScreenView = mainScreenView;
        this.gameRulesView = gameRulesScreenView;
        this.mainScreenView.showMainScreen();
        this.terminal = terminal;
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
        Result result = Result.IS_RUNNING;
        switch (command) {
            case UP:
                result = game.manageGame(Action.MOVE_UP);
                break;
            case DOWN:
                result = game.manageGame(Action.MOVE_DOWN);
                break;
            case LEFT:
                result = game.manageGame(Action.MOVE_LEFT);
                break;
            case RIGHT:
                result = game.manageGame(Action.MOVE_RIGHT);
                break;
            case BACK_SLASH:
                game.manageGame(Action.CHANGE_EQUIPTION);
                break;
            case SPACE:
                game.manageGame(Action.DESTROY);
        }
        if (result != Result.IS_RUNNING) {
            screen = Screen.MAIN_MENU;
            mainScreenView.showMainScreen();
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
                        game.startGame(false, terminal);
                        break;
                }
        }
    }
}
