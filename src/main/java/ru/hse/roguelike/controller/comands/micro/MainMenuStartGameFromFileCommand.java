package ru.hse.roguelike.controller.comands.micro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.GameState;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

public class MainMenuStartGameFromFileCommand implements MicroCommand {
    private final Game game;

    private final AbstractViewFactory factory;

    public MainMenuStartGameFromFileCommand(Game game, AbstractViewFactory factory) {
        this.game = game;
        this.factory = factory;
    }

    @Override
    public Screen execute() {
        GameState state1 = game.startGame(true, factory);
        if (state1 != GameState.IS_RUNNING) {
            System.out.println("Problem with starting game");
            return Screen.MAIN_MENU;
        }
        return Screen.GAME;
    }
}
