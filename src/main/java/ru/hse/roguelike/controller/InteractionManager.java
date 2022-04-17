package ru.hse.roguelike.controller;

import java.io.IOException;

import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.GameState;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.MainScreenView;
import ru.hse.roguelike.view.abstract_view.GameRulesScreenView;

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
    private final AbstractViewFactory factory;

    /**
     * Creates new InteractionManager instance.
     *
     * @param filesPath config files path
     * @param factory   AbstractViewFactory Implementation
     **/
    public InteractionManager(String filesPath, AbstractViewFactory factory) throws IOException {
        this.mainScreenView = factory.createMainScreenView();
        this.gameRulesView = factory.createGameRulesScreenView();
        this.mainScreenView.showMainScreen();
        this.factory = factory;
        this.game = new Game(filesPath);
    }

    /**
     * Creates new InteractionManager instance.
     * Used only for tests to provide opportunity to mock class Game.
     *
     * @param factory AbstractViewFactory Implementation
     * @param game    Game object
     */

    public InteractionManager(AbstractViewFactory factory, Game game) throws IOException {
        this.mainScreenView = factory.createMainScreenView();
        this.gameRulesView = factory.createGameRulesScreenView();
        this.mainScreenView.showMainScreen();
        this.factory = factory;
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
     * @throws IOException in case of terminal creation error
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
        GameState gameState = GameState.IS_RUNNING;
        switch (command) {
            case UP:
                gameState = game.manageGame(Action.MOVE_UP);
                break;
            case DOWN:
                gameState = game.manageGame(Action.MOVE_DOWN);
                break;
            case LEFT:
                gameState = game.manageGame(Action.MOVE_LEFT);
                break;
            case RIGHT:
                gameState = game.manageGame(Action.MOVE_RIGHT);
                break;
            case BACKSLASH:
                game.manageGame(Action.CHANGE_EQUIPTION);
                break;
            case SPACE:
                game.manageGame(Action.DESTROY);
        }
        if (gameState != GameState.IS_RUNNING) {
            screen = Screen.MAIN_MENU;
            mainScreenView.showMainScreen();
        }
    }

    private void processCommandMainMenu(InputCommand command) {
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
                        GameState state1 = game.startGame(true, factory);
                        if (state1 != GameState.IS_RUNNING) {
                            return;
                        }
                        screen = Screen.GAME;
                        break;
                    case START_GAME:
                        GameState state2 = game.startGame(false, factory);
                        if (state2 != GameState.IS_RUNNING) {
                            return;
                        }
                        screen = Screen.GAME;
                        break;
                }
        }
    }
}
