package ru.hse.roguelike.model;

import ru.hse.roguelike.view.GameScreenView;

import java.util.Map;

public class Level {
    private int[][] board;
    private Map<Integer, Character> characters;
    private GameScreenView gameView;
    private LevelTimer levelTimer;

    public Level(int[][] board, Map<Integer, Character> characters,
                 GameScreenView gameView, LevelTimer levelTimer) {
        this.board = board;
        this.characters = characters;
        this.gameView = gameView;
        this.levelTimer = levelTimer;
    }

    private LevelTimer createLevelTimer() {
        return new LevelTimer();
    }

    public void Play() {
        LevelTimer levelTimer = createLevelTimer();
        levelTimer.startCountDown();
    }

    public void movePlayer() {
        throw new UnsupportedOperationException();
    }

    public void moveEnemies() {
        throw new UnsupportedOperationException();
    }

    public void changeEquiption() {
        throw new UnsupportedOperationException();
    }

    public void attackFromPlayer() {
        throw new UnsupportedOperationException();
    }
}
