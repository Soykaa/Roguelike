package ru.hse.roguelike.controller.comands.macro;

import ru.hse.roguelike.controller.Screen;
import ru.hse.roguelike.controller.input.InputCommand;
import ru.hse.roguelike.model.Action;
import ru.hse.roguelike.model.Game;
import ru.hse.roguelike.model.GameState;
import ru.hse.roguelike.view.abstract_view.MainScreenView;

public class GameMacroCommand implements MacroCommand {
    private final Game game;
    private final MainScreenView mainScreenView;

    public GameMacroCommand(Game game, MainScreenView mainScreenView) {
        this.game = game;
        this.mainScreenView = mainScreenView;
    }


    @Override
    public Screen execute(InputCommand inputCommand) {
        GameState gameState = GameState.IS_RUNNING;
        switch (inputCommand) {
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
                gameState = game.manageGame(Action.DESTROY);
                break;
            case UNKNOWN_COMMAND:
                gameState = game.manageGame(Action.UNKNOWN_ACTION);
                break;
        }
        if (gameState != GameState.IS_RUNNING) {
            mainScreenView.showMainScreen();
            return Screen.MAIN_MENU;
        }
        return Screen.GAME;
    }

}
