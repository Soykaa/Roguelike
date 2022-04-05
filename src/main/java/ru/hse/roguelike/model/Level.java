package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.*;
import ru.hse.roguelike.view.GameScreenView;

import java.io.IOException;

public class Level {
    private GameCharacter[][] board;
    private GameScreenView gameView;
    private Player player;
    private final int victoryPoints = 10;

    public Level(GameCharacter[][] board, GameScreenView gameView, Player player) {
        this.board = board;
        this.gameView = gameView;
        this.player = player;
    }

    public Result movePlayer(int dx, int dy) throws IOException {
        Coordinates currentCoordinates = player.getCurrentCoordinates();
        int newX = currentCoordinates.getX() + dx;
        int newY = currentCoordinates.getY() + dy;
        if (newX >= 0 && newX < board.length && newY < board[0].length && newY >= 0) {
            GameCharacter nextCell = board[newX][newY];
            switch (nextCell.getCharacterType()) {
                case EMPTY:
                    break;
                case POINTS:
                    var points = (Points)nextCell;
                    player.increasePoints(points.getNumberOfPoints());
                    break;
                default:
                    return Result.IS_RUNNING;
            }
            board[currentCoordinates.getX()][currentCoordinates.getY()] = new Empty();
            gameView.removeCharacter(currentCoordinates.getX(), currentCoordinates.getY());
            currentCoordinates.setX(newX);
            currentCoordinates.setY(newY);
            board[currentCoordinates.getX()][currentCoordinates.getY()] = player;
            gameView.placeCharacter(player, newX, newY);
            if (player.getPoints() >= victoryPoints) {
                return Result.VICTORY;
            }
        }
        return Result.IS_RUNNING;
    }

    public Result moveEnemies() {
        throw new UnsupportedOperationException();
    }

    public Result moveCharacters() {
        throw new UnsupportedOperationException();
    }

    public void changeEquiption() {
        throw new UnsupportedOperationException();
    }

    public void attackFromPlayer() {
        throw new UnsupportedOperationException();
    }
}
