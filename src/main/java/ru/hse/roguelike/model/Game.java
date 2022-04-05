package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.view.GameScreenView;

import java.io.IOException;

public class Game {
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private String levelFilesPath = "";

    public Game(String levelFilesPath) {
        this.levelFilesPath = levelFilesPath;
    }

    public Result startGame(boolean generateLevelsFromFile, GameScreenView gameView) throws IOException {
        GameCharacter [][] board = new GameCharacter[2][2];
        var player = new Player(3, 3);
        board[0][0] = player;
        board[1][0] = new Empty();
        board[0][1] = new Empty();
        board[1][1] = new Empty();
        currentLevel = new Level(board, gameView, player);
        gameView.showBoard(board);
        return Result.VICTORY;
    }

    public Result makeAction(Action action) throws IOException {
        switch (action) {
            case ATTACK:
                currentLevel.attackFromPlayer();
                break;
            case MOVE_DOWN:
                currentLevel.movePlayer(0, 1);
                break;
            case MOVE_LEFT:
                currentLevel.movePlayer(-1, 0);
                break;
            case MOVE_RIGHT:
                currentLevel.movePlayer(1, 0);
                break;
            case MOVE_UP:
                currentLevel.movePlayer(0, -1);
                break;
            case CHANGE_EQUIPTION:
                currentLevel.changeEquiption();
                break;
        }
        return Result.IS_RUNNING;
    }
}
