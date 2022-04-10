package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.*;
import ru.hse.roguelike.view.abstract_view.GameScreenView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Level {
    private final GameCharacter[][] board;
    private final GameScreenView gameView;
    private final Player player;
    private final int victoryPoints;
    private final Map<Enemy, Coordinates> enemies;
    private final CharacterType realShelterType;
    private CharacterType playerShelter = null;

    public Level(GameCharacter[][] board, GameScreenView gameView, Player player,
                 Map<Enemy, Coordinates> enemies, CharacterType realShelterType, int victoryPoints) {
        this.board = board;
        this.gameView = gameView;
        this.player = player;
        this.enemies = enemies;
        this.realShelterType = realShelterType;
        this.victoryPoints = victoryPoints;
        try {
            gameView.showBoard(board);
            gameView.showLives(player.getLives());
            gameView.showBackpack(player.getBackpack());
            gameView.showPoints(player.getPoints(), victoryPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveCharacter(GameCharacter characterToMove, Coordinates currentCoordinates, int newX, int newY) throws IOException {
        gameView.moveCharacter(currentCoordinates.getX(), currentCoordinates.getY(), newX, newY);
        board[currentCoordinates.getX()][currentCoordinates.getY()] = new Empty();
        currentCoordinates.setX(newX);
        currentCoordinates.setY(newY);
        board[currentCoordinates.getX()][currentCoordinates.getY()] = characterToMove;
    }

    private boolean isValidCoordinates(int x, int y) {
        return x >= 0 && x < board.length && y < board[0].length && y >= 0;
    }

    private boolean charactersAreClose(Coordinates first, Coordinates second) {
        if (first.getX() == second.getX()) {
            return Math.abs(first.getY() - second.getY()) <= 1;
        }
        if (first.getY() == second.getY()) {
            return Math.abs(first.getX() - second.getX()) <= 1;
        }
        return false;
    }

    private Result movePlayer(int dx, int dy) throws IOException {
        Coordinates currentCoordinates = player.getCurrentCoordinates();
        int newX = currentCoordinates.getX() + dx;
        int newY = currentCoordinates.getY() + dy;
        if (isValidCoordinates(newX, newY)) {
            GameCharacter nextCell = board[newX][newY];
            switch (nextCell.getCharacterType()) {
                case EMPTY:
                    playerShelter = null;
                    break;
                case POINTS:
                    var points = (Points) nextCell;
                    player.increasePoints(points.getNumberOfPoints());
                    playerShelter = null;
                    gameView.showPoints(player.getPoints(), victoryPoints);
                    break;
                case INVENTORY:
                    var inventory = (Inventory) nextCell;
                    player.getBackpack().putItem(inventory);
                    gameView.showBackpack(player.getBackpack());
                    break;
                case SHELTER_LAVENDER:
                    playerShelter = CharacterType.SHELTER_LAVENDER;
                    break;
                case SHELTER_PINK:
                    playerShelter = CharacterType.SHELTER_PINK;
                    break;
                case SHELTER_YELLOW:
                    playerShelter = CharacterType.SHELTER_YELLOW;
                    break;
                default:
                    return Result.IS_RUNNING;
            }
            moveCharacter(player, currentCoordinates, newX, newY);
            if (player.getPoints() >= victoryPoints) {
                return Result.VICTORY;
            }
        }
        return Result.IS_RUNNING;
    }

    private Result moveEnemies() throws IOException {
        for (var entry : enemies.entrySet()) {
            Enemy enemy = entry.getKey();
            Coordinates coordinates = entry.getValue();
            Coordinates shift = enemy.makeNextMove();
            int newX = coordinates.getX() + shift.getX();
            int newY = coordinates.getY() + shift.getY();
            if (isValidCoordinates(newX, newY)) {
                if (board[newX][newY].getCharacterType() == CharacterType.EMPTY) {
                    moveCharacter(enemy, coordinates, newX, newY);
                }
            }
            if (charactersAreClose(player.getCurrentCoordinates(), coordinates)) {
                if (playerShelter == null | playerShelter != realShelterType) {
                    enemy.attack(player);
                }
            }
            gameView.showLives(player.getLives());
            if (player.getLives() <= 0) {
                return Result.DEFEAT;
            }
        }
        return Result.IS_RUNNING;
    }

    public Result moveCharacters(int dx, int dy) throws IOException {
        Result movePlayerResult = movePlayer(dx, dy);
        if (movePlayerResult != Result.IS_RUNNING) {
            return movePlayerResult;
        }
        return moveEnemies();
    }

    public void changeEquiption() throws IOException {
        player.getBackpack().setNextActiveItem();
        gameView.showBackpack(player.getBackpack());
    }

    public Result destroyObstacle() throws IOException {
        if (!player.canDestroy()) {
            return Result.IS_RUNNING;
        }
        List<Coordinates> neighbours = List.of(new Coordinates(1, 0),
                new Coordinates(-1, 0),
                new Coordinates(0, 1),
                new Coordinates(0, -1)
        );
        for (var neighbour : neighbours) {
            int x = player.getCurrentCoordinates().getX() + neighbour.getX();
            int y = player.getCurrentCoordinates().getY() + neighbour.getY();
            if (!isValidCoordinates(x, y) || board[x][y].getCharacterType() != CharacterType.OBSTACLE) {
                continue;
            }
            player.increasePoints(((Obstacle) board[x][y]).getDestroyBonus());
            board[x][y] = new Empty();
            gameView.showPoints(player.getPoints(), victoryPoints);
            gameView.removeCharacter(x, y);
            if (player.getPoints() >= victoryPoints) {
                return Result.VICTORY;
            }
        }
        return Result.IS_RUNNING;
    }
}
