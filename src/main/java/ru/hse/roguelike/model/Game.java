package ru.hse.roguelike.model;

import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

import java.io.IOException;

/**
 * Represents game itself.
 **/
public class Game {
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private final String levelFilesPath;

    /**
     * Creates new Game instance.
     *
     * @param levelFilesPath path to config files
     **/
    public Game(String levelFilesPath) {
        this.levelFilesPath = levelFilesPath;
    }

    /**
     * Starts game.
     * While the player is alive, or it's not game end - runs new levels one by one.
     *
     * @param generateLevelsFromFile flag, determines if the level should be generated from file or not
     * @param factory                view factory
     **/
    public void startGame(boolean generateLevelsFromFile, AbstractViewFactory factory) {
        if (generateLevelsFromFile) {
            levelGenerator = new LevelGenerator(levelFilesPath, factory);
        } else {
            levelGenerator = new LevelGenerator(factory, 5);
        }
        if (!levelGenerator.hasNextLevel()) {
            throw new RuntimeException("Wrong level generation");
        }
        currentLevel = levelGenerator.nextLevel();
    }

    /**
     * Determines current game state after some action.
     * In case of victory, starts new level if it exists.
     *
     * @param action action to make
     * @return current game state
     * @throws IOException in case of view error
     **/
    public GameState manageGame(Action action) throws IOException {
        GameState gameState = makeAction(action);
        if (gameState == GameState.VICTORY) {
            if (levelGenerator.hasNextLevel()) {
                currentLevel = levelGenerator.nextLevel();
                return GameState.IS_RUNNING;
            } else {
                return gameState;
            }
        }
        return gameState;
    }

    /**
     * Makes passed action.
     *
     * @param action action to make
     * @return current game state
     * @throws IOException in case of view error
     **/
    public GameState makeAction(Action action) throws IOException {
        GameState gameState = GameState.IS_RUNNING;
        switch (action) {
            case DESTROY:
                gameState = currentLevel.destroyObstacle();
                break;
            case MOVE_DOWN:
                gameState = currentLevel.moveCharacters(0, 1);
                break;
            case MOVE_LEFT:
                gameState = currentLevel.moveCharacters(-1, 0);
                break;
            case MOVE_RIGHT:
                gameState = currentLevel.moveCharacters(1, 0);
                break;
            case MOVE_UP:
                gameState = currentLevel.moveCharacters(0, -1);
                break;
            case CHANGE_EQUIPTION:
                currentLevel.changeEquiption();
                break;
        }
        return gameState;
    }
}
