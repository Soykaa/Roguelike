package ru.hse.roguelike.controller;

import java.io.IOException;

import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.Result;
import ru.hse.roguelike.view.MainScreenView;
import ru.hse.roguelike.view.GameRulesScreenView;

/**
 * Responsible for all user actions outside the gameplay itself,
 * such as actions on the initial screen (request to start the game / display the rules / exit the application),
 * as well as on the screen with the rules of the game.
 **/
public class InteractionManager {
    private Screen screen = Screen.MAIN_MENU;
    private final MainScreenView mainScreenView;
    private final ItemHolder itemHolder = new ItemHolder();
    private final GameRulesScreenView gameRulesView;
    private final Game game;
    public boolean isRunning = true;
    private final Terminal terminal;

    /**
     * Creates new InteractionManager instance.
     *
     * @param filesPath           config files path
     * @param mainScreenView      MainScreenView object
     * @param gameRulesScreenView GameRulesScreenView object
     * @param terminal            Terminal object
     **/
    public InteractionManager(String filesPath, MainScreenView mainScreenView, GameRulesScreenView gameRulesScreenView,
                              Terminal terminal) {
        this.mainScreenView = mainScreenView;
        this.gameRulesView = gameRulesScreenView;
        this.mainScreenView.showMainScreen();
        this.terminal = terminal;
        this.game = new Game(filesPath);
    }

    /**
     * Creates new InteractionManager instance.
     * Used only for tests to provide opportunity to mock class Game.
     *
     * @param mainScreenView      mainScreenView object
     * @param gameRulesScreenView GameRulesScreenView object
     * @param terminal            Terminal object
     * @param game                Game object
     */

    public InteractionManager(MainScreenView mainScreenView, GameRulesScreenView gameRulesScreenView,
                              Terminal terminal, Game game) {
        this.mainScreenView = mainScreenView;
        this.gameRulesView = gameRulesScreenView;
        this.mainScreenView.showMainScreen();
        this.terminal = terminal;
        this.game = game;
    }

    /**
     * Returns current screen.
     *
     * @return screen
     **/
    public Screen getScreen() {
        return screen;
    }

    /**
     * Handles a keystroke on initial / rules / game screen.
     *
     * @param command keystroke to handle
     **/
    public void processCommand(InputCommand command) throws IOException {
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
                    case START_GAME_FROM_FILE:
                        screen = Screen.GAME;
                        game.startGame(true, terminal);
                        break;
                    case START_GAME:
                        screen = Screen.GAME;
                        game.startGame(false, terminal);
                        break;
                }
        }
    }
}
