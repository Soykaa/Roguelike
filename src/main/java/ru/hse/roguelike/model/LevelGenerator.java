package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.Enemy;
import ru.hse.roguelike.model.Characters.EnemyStrong;
import ru.hse.roguelike.model.Characters.EnemyWeak;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Inventory;
import ru.hse.roguelike.model.Characters.Obstacle;
import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.Characters.Points;
import ru.hse.roguelike.model.Characters.Shelter;
import ru.hse.roguelike.model.LevelCharacteristics.FifthLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.FirstLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.FourthLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.LevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.SecondLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.ThirdLevelCharacteristic;
import ru.hse.roguelike.view.GameScreenView;
import ru.hse.roguelike.view.GameScreenViewConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


public class LevelGenerator {
    private Optional<String> filesPath;
    private int levelNumber = -1;
    private final int maxLevelAmount = 5;
    private List<LevelCharacteristic> levelCharacteristics = new ArrayList<>();
    private final Terminal terminal;
    private final Player player;
    Random rand = new Random();

    public LevelGenerator(Optional<String> filesPath, Terminal terminal) {
        this.filesPath = filesPath;
        levelCharacteristics.add(new FirstLevelCharacteristic());
        levelCharacteristics.add(new SecondLevelCharacteristic());
        levelCharacteristics.add(new ThirdLevelCharacteristic());
        levelCharacteristics.add(new FourthLevelCharacteristic());
        levelCharacteristics.add(new FifthLevelCharacteristic());
        this.terminal = terminal;
        this.player = generateRandomPlayer();
    }

    public Optional<String> getFilesPath() {
        return filesPath;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public List<LevelCharacteristic> getLevelCharacteristics() {
        return levelCharacteristics;
    }

    public boolean hasNextLevel() {
        return levelNumber + 1 < maxLevelAmount;
    }

    private Player generateRandomPlayer() {
        int lives = rand.nextInt(10) + 1;
        return new Player(lives, 0);
    }

    private EnemyWeak generateRandomEnemy() {
        int maxStep = rand.nextInt(4) + 1;
        int randomShift = rand.nextInt(4);
        List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1));
        return new EnemyWeak(maxStep, shifts.get(randomShift));
    }

    private Points generatePoints() {
        int points = rand.nextInt(9) + 1;
        return new Points(points);
    }


    public Level nextLevel() {
        levelNumber++;
        player.setPoints(0);
        player.getBackpack().clear();
        LevelCharacteristic levelCharacteristic = levelCharacteristics.get(levelNumber);
        GameCharacter[][] board = new GameCharacter[levelCharacteristic.getX()][levelCharacteristic.getY()];
        Map<Enemy, Coordinates> enemies = new HashMap<>();
        int victoryPoints = 0;
        for (var entry : levelCharacteristic.getCharactersToPlace().entrySet()) {
            int characterNumber = entry.getValue();
            for (int i = 0; i < characterNumber; i++) {
                var coordinates = levelCharacteristic.getRandomCell();
                GameCharacter characterToPlace;
                switch (entry.getKey()) {
                    case PLAYER:
                        player.setCurrentCoordinates(coordinates);
                        characterToPlace = player;
                        break;
                    case POINTS:
                        characterToPlace = generatePoints();
                        victoryPoints += ((Points) characterToPlace).getNumberOfPoints();
                        break;
                    case ENEMY_STRONG:
                        var enemyStrong = new EnemyStrong();
                        characterToPlace = enemyStrong;
                        enemies.put(enemyStrong, coordinates);
                        break;
                    case ENEMY_WEAK:
                        var enemyWeak = generateRandomEnemy();
                        characterToPlace = enemyWeak;
                        enemies.put(enemyWeak, coordinates);
                        break;
                    case OBSTACLE:
                        characterToPlace = new Obstacle();
                        break;
                    case SHELTER_LAVENDER:
                    case SHELTER_PINK:
                    case SHELTER_YELLOW:
                        characterToPlace = new Shelter(entry.getKey());
                        break;
                    case INVENTORY:
                        if (i == 1) {
                            characterToPlace = new Inventory(InventoryItem.PROTECTION);
                        } else {
                            characterToPlace = new Inventory(InventoryItem.DESTROY);
                        }
                        break;
                    default:
                        characterToPlace = new Empty();
                }
                board[coordinates.getX()][coordinates.getY()] = characterToPlace;
            }
        }

        while (levelCharacteristic.haveEmptyCells()) {
            var coordinates = levelCharacteristic.getRandomCell();
            board[coordinates.getX()][coordinates.getY()] = new Empty();
        }

        GameScreenView gameScreenView = null;
        try {
            gameScreenView = new GameScreenViewConsole(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Level(board, gameScreenView, player, enemies, levelCharacteristic.getShelterType(), victoryPoints);
    }
}
