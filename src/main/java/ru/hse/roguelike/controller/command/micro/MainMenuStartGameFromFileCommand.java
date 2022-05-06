package ru.hse.roguelike.controller.command.micro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.GameState;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

/**
 * Represents "start game from file" option in main menu.
 **/
public class MainMenuStartGameFromFileCommand implements MicroCommand {
    private final Game game;
    private final AbstractViewFactory factory;

    /**
     * Creates new MainMenuStartGameFromFileCommand instance.
     *
     * @param game    game
     * @param factory abstract view factory
     **/
    public MainMenuStartGameFromFileCommand(Game game, AbstractViewFactory factory) {
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
        GameState gameState = game.startGame(true, factory);
        if (gameState != GameState.IS_RUNNING) {
            System.out.println("Problem with starting game");
            return Screen.MAIN_MENU;
        }
        return Screen.GAME;
    }
}
