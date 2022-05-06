package ru.hse.roguelike.model;

import ru.hse.roguelike.model.level_builder.FromFileLevelBuilder;
import ru.hse.roguelike.model.level_builder.RandomLevelBuilder;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

import java.io.IOException;

/**
 * Represents game itself.
 **/
public class Game {
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private final String levelFilesPath;
    private GameState previousGameState = GameState.IS_RUNNING;

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
    public GameState startGame(boolean generateLevelsFromFile, AbstractViewFactory factory) {
        levelGenerator = new LevelGenerator(factory);
        previousGameState = GameState.IS_RUNNING;
        if (generateLevelsFromFile) {
            levelGenerator.setLevelBuilder(new FromFileLevelBuilder(levelFilesPath));
        } else {
            levelGenerator.setLevelBuilder(new RandomLevelBuilder());

        }
        currentLevel = levelGenerator.getNextLevel();
        if (currentLevel == null) {
            return GameState.PROBLEM_OCCURRED;
        }
        return GameState.IS_RUNNING;
    }

    /**
     * Determines current game state after some action.
     * In case of victory, starts new level if it exists.
     *
     * @param action action to make
     * @return current game state
     **/
    public GameState manageGame(Action action) {
        if (previousGameState != GameState.IS_RUNNING) {
            return previousGameState;
        }
        if (action == Action.UNKNOWN_ACTION) {
            return previousGameState;
        }
        try {
            GameState gameState = makeAction(action);
            var gameView = currentLevel.getGameView();
            if (gameState == GameState.VICTORY) {
                currentLevel = levelGenerator.getNextLevel();
                if (currentLevel == null) {
                    gameView.setMessage("You WIN!!!\nPress any key to exit");
                    previousGameState = GameState.VICTORY;
                }
                return GameState.IS_RUNNING;
            } else if (gameState == GameState.DEFEAT) {
                gameView.setMessage("You lose :(\nPress any key to exit");
                previousGameState = GameState.DEFEAT;
                return GameState.IS_RUNNING;
            }
            return gameState;
        } catch (Exception e) {
            System.out.println("Problem occurred while playing game: " + e.getMessage());
            return GameState.PROBLEM_OCCURRED;
        }
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
