package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;

public class Game {
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private final String levelFilesPath;

    public Game(String levelFilesPath) {
        this.levelFilesPath = levelFilesPath;
    }

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

    public Result manageGame(Action action) throws IOException {
        Result result = makeAction(action);
        if (result == Result.VICTORY) {
            if (levelGenerator.hasNextLevel()) {
                currentLevel = levelGenerator.nextLevel();
                return Result.IS_RUNNING;
            } else {
                return result;
            }
        }
        return result;
    }

    public Result makeAction(Action action) throws IOException {
        Result result = Result.IS_RUNNING;
        switch (action) {
            case DESTROY:
                result = currentLevel.destroyObstacle();
                break;
            case MOVE_DOWN:
                result = currentLevel.moveCharacters(0, 1);
                break;
            case MOVE_LEFT:
                result = currentLevel.moveCharacters(-1, 0);
                break;
            case MOVE_RIGHT:
                result = currentLevel.moveCharacters(1, 0);
                break;
            case MOVE_UP:
                result = currentLevel.moveCharacters(0, -1);
                break;
            case CHANGE_EQUIPTION:
                currentLevel.changeEquiption();
                break;
        }
        return result;
    }
}
