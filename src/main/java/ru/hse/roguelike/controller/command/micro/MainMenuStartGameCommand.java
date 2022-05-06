package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.GameState;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

/**
 * Represents "start game" option in main menu.
 **/
public class MainMenuStartGameCommand implements MicroCommand {
    private final Game game;
    private final AbstractViewFactory factory;

    /**
     * Creates new MainMenuStartGameCommand instance.
     *
     * @param game    game
     * @param factory abstract view factory
     **/
    public MainMenuStartGameCommand(Game game, AbstractViewFactory factory) {
        this.game = game;
        this.factory = factory;
    }

    /**
     * Executes command.
     *
     * @return game screen
     **/
    @Override
    public Screen execute() {
        GameState gameState = game.startGame(false, factory);
        if (gameState != GameState.IS_RUNNING) {
            System.out.println("Problem with starting game");
            return Screen.MAIN_MENU;
        }
        return Screen.GAME;
    }
}
