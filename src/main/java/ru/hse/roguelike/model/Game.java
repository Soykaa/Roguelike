package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class Game {
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private String levelFilesPath = "";

    public Game(String levelFilesPath) {
        this.levelFilesPath = levelFilesPath;
    }

    public void startGame(boolean generateLevelsFromFile, Terminal terminal) throws IOException {
        levelGenerator = new LevelGenerator(Optional.empty(), terminal);
        if (!levelGenerator.hasNextLevel()) {
            throw new RuntimeException("Wrong level generation");
        }
        currentLevel = levelGenerator.nextLevel();
    }

    public Result manageGame(Action action) throws IOException {
        Result result = makeAction(action);
        switch (result) {
            case VICTORY:
                if (levelGenerator.hasNextLevel()) {
                    currentLevel = levelGenerator.nextLevel();
                    return Result.IS_RUNNING;
                } else {
                    return result;
                }
            default:
                return result;
        }
    }

    public Result makeAction(Action action) throws IOException {
        Result result = Result.IS_RUNNING;
        switch (action) {
            case ATTACK:
                currentLevel.attackFromPlayer();
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
