package ru.hse.roguelike.model;

import java.util.HashMap;
import java.util.Random;

import ru.hse.roguelike.model.character.*;
import ru.hse.roguelike.model.character.mob.Mob;
import ru.hse.roguelike.model.character.mob.decorator.ConfusedMobDecorator;
import ru.hse.roguelike.view.abstract_view.GameScreenView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents game level.
 **/
public class Level {
    private final GameCharacter[][] board;
    private GameScreenView gameView;
    private final Player player;
    private final int victoryPoints;
    private final Map<Mob, Coordinates> enemies;
    private final CharacterType realShelterType;
    private CharacterType playerShelter = null;

    private final List<Mob> confusedEnemies = new ArrayList<>();
    private final List<Mob> killedEnemies = new ArrayList<>();


    /**
     * Creates new Level instance.
     *
     * @param board           game board
     * @param player          player
     * @param enemies         enemies with their coordinates
     * @param realShelterType valid shelter type
     * @param victoryPoints   number of points to win
     **/
    public Level(GameCharacter[][] board, Player player,
                 Map<Mob, Coordinates> enemies, CharacterType realShelterType, int victoryPoints) {
        this.board = board;
        this.player = player;
        this.enemies = enemies;
        this.realShelterType = realShelterType;
        this.victoryPoints = victoryPoints;
    }

    /**
     * Returns game screen view.
     *
     * @return game view
     **/
    public GameScreenView getGameView() {
        return gameView;
    }

    /**
     * Returns game board.
     *
     * @return board
     **/
    public GameCharacter[][] getBoard() {
        return board;
    }

    /**
     * Sets game screen view.
     *
     * @param gameView game view
     **/
    public void setGameView(GameScreenView gameView) {
        this.gameView = gameView;
        try {
            gameView.showBoard(board);
            gameView.showLives(player.getLives());
            gameView.showBackpack(player.getBackpack());
            gameView.showPoints(player.getPoints(), victoryPoints);
            gameView.showExperience(player.getExperience(), player.getExperienceIncreaseForNextLevel());
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

    private Coordinates findFreeCoordinatesNearby(int x, int y) {
        if (isValidCoordinates(x + 1, y) && board[x + 1][y].getCharacterType() == CharacterType.EMPTY) {
            return new Coordinates(x + 1, y);
        }
        if (isValidCoordinates(x - 1, y) && board[x - 1][y].getCharacterType() == CharacterType.EMPTY) {
            return new Coordinates(x - 1, y);
        }
        if (isValidCoordinates(x, y + 1) && board[x][y + 1].getCharacterType() == CharacterType.EMPTY) {
            return new Coordinates(x, y + 1);
        }
        if (isValidCoordinates(x, y - 1) && board[x][y - 1].getCharacterType() == CharacterType.EMPTY) {
            return new Coordinates(x, y - 1);
        }
        return new Coordinates(-1, -1);
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

    private GameState movePlayer(int dx, int dy) throws IOException {
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
                    playerShelter = null;
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
                    return GameState.IS_RUNNING;
            }
            moveCharacter(player, currentCoordinates, newX, newY);
            if (player.getPoints() >= victoryPoints) {
                return GameState.VICTORY;
            }
        }
        player.decreaseWaitForConfusion();
        gameView.showBackpack(player.getBackpack());
        return GameState.IS_RUNNING;
    }

    private GameState moveEnemies() throws IOException {
        Map<Mob, Coordinates> newEnemies = new HashMap<>();
        for (var entry : enemies.entrySet()) {
            Mob enemy = entry.getKey();
            Coordinates coordinates = entry.getValue();
            Coordinates shift = enemy.makeNextMove(coordinates, player.getCurrentCoordinates());
            int newX = coordinates.getX() + shift.getX();
            int newY = coordinates.getY() + shift.getY();
            if (isValidCoordinates(newX, newY)) {
                if (board[newX][newY].getCharacterType() == CharacterType.EMPTY) {
                    moveCharacter(enemy, coordinates, newX, newY);
                }
            }
            if (charactersAreClose(player.getCurrentCoordinates(), coordinates)) {
                makeBattle(enemy, coordinates);
            }
            gameView.showLives(player.getLives());

            if (new Random().nextFloat() < enemy.getReplicationProbability()
                    && isValidCoordinates(newX, newY)
                    && board[newX][newY].getCharacterType() == enemy.getCharacterType()
            ) {
                Coordinates newEnemyCoordinates = findFreeCoordinatesNearby(newX, newY);
                int newEnemyX = newEnemyCoordinates.getX();
                int newEnemyY = newEnemyCoordinates.getY();
                if (newEnemyX != -1 && newEnemyY != -1) {
                    Mob newEnemy = enemy.cloneMob();
                    newEnemies.put(newEnemy, newEnemyCoordinates);
                }
            }
            if (player.getLives() <= 0) {
                return GameState.DEFEAT;
            }
        }
        for (var enemy : newEnemies.entrySet()) {
            Coordinates newEnemyCoordinates = enemy.getValue();
            int newEnemyX = newEnemyCoordinates.getX();
            int newEnemyY = newEnemyCoordinates.getY();
            if (board[newEnemyX][newEnemyY].getCharacterType() == CharacterType.EMPTY) {
                board[newEnemyX][newEnemyY] = enemy.getKey();
                gameView.placeCharacter(enemy.getKey(), newEnemyX, newEnemyY);
                enemies.put(enemy.getKey(), enemy.getValue());
            }
        }
        changeConfusedEnemies();
        confusedEnemies.clear();
        deleteKilledEnemies();
        killedEnemies.clear();
        return GameState.IS_RUNNING;
    }

    private void changeConfusedEnemies() {
        for (var enemy : confusedEnemies) {
            var coordinates = enemies.get(enemy);
            if (coordinates == null) {
                return;
            }
            enemies.remove(enemy);
            Mob newEnemy = new ConfusedMobDecorator(enemy);
            board[coordinates.getX()][coordinates.getY()] = newEnemy;
            enemies.put(newEnemy, coordinates);
        }
    }

    private void deleteKilledEnemies() {
        for (var enemy : killedEnemies) {
            var coordinates = enemies.get(enemy);
            if (coordinates == null) {
                return;
            }
            enemies.remove(enemy);
        }
    }

    private void makeBattle(Mob enemy, Coordinates coordinates) throws IOException {
        if (player.canConfuse()) {
            player.confuse();
            confusedEnemies.add(enemy);
            player.increaseExperience(5);
            gameView.showBackpack(player.getBackpack());
            return;
        }

        if (player.canDestroy()) {
            killedEnemies.add(enemy);
            player.increaseExperience(5);
            board[coordinates.getX()][coordinates.getY()] = new Empty();
            gameView.removeCharacter(coordinates.getX(), coordinates.getY());
            gameView.showExperience(player.getExperience(), player.getExperienceIncreaseForNextLevel());
        }

        if (playerShelter == null | playerShelter != realShelterType) {
            enemy.attack(player);
        }
    }

    /**
     * Moves game characters.
     *
     * @param dx delta to move on axis X
     * @param dy delta to move on axis Y
     * @return current game state
     * @throws IOException in case of view error
     **/
    public GameState moveCharacters(int dx, int dy) throws IOException {
        GameState movePlayerGameState = movePlayer(dx, dy);
        if (movePlayerGameState != GameState.IS_RUNNING) {
            return movePlayerGameState;
        }
        return moveEnemies();
    }

    /**
     * Changes player equiption.
     *
     * @throws IOException in case of view error
     **/
    public void changeEquiption() throws IOException {
        player.getBackpack().setNextActiveItem();
        gameView.showBackpack(player.getBackpack());
    }

    /**
     * Destroys obstacle, if it's possible.
     *
     * @return current game state
     * @throws IOException in case of view error
     **/
    public GameState destroyObstacle() throws IOException {
        if (!player.canDestroy()) {
            return GameState.IS_RUNNING;
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
                return GameState.VICTORY;
            }
        }
        return GameState.IS_RUNNING;
    }
}
