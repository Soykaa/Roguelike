package ru.hse.roguelike.model;

public class Game {
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private String levelFilesPath = "";

    public Game(String levelFilesPath) {
        this.levelFilesPath = levelFilesPath;
    }

    Result startGame(boolean generateLevelsFromFile) {
        throw new UnsupportedOperationException();
    }

    void makeAction(Action action) {
        switch (action) {
            case ATTACK -> currentLevel.attackFromPlayer();
            case MOVE_DOWN -> currentLevel.movePlayer();
            case MOVE_LEFT -> currentLevel.movePlayer();
            case MOVE_RIGHT -> currentLevel.movePlayer();
            case MOVE_UP -> currentLevel.movePlayer();
            case CHANGE_EQUIPTION -> currentLevel.changeEquiption();
        }
    }
}
