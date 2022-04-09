package ru.hse.roguelike.model;

import com.googlecode.lanterna.terminal.Terminal;
import org.json.JSONObject;
import ru.hse.roguelike.model.Characters.CharacterType;
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

import org.json.JSONArray;
import ru.hse.roguelike.view.GameScreenViewConsole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;


public class LevelGenerator {
    private final Optional<String> filesPath;
    private int levelNumber = -1;
    private final int maxLevelAmount;
    private final List<LevelCharacteristic> levelCharacteristics = new ArrayList<>();
    private final Terminal terminal;
    private Player player;
    Random rand = new Random();

    public LevelGenerator(Terminal terminal, int maxLevelAmount) {
        this.filesPath = Optional.empty();
        levelCharacteristics.add(new FirstLevelCharacteristic());
        levelCharacteristics.add(new SecondLevelCharacteristic());
        levelCharacteristics.add(new ThirdLevelCharacteristic());
        levelCharacteristics.add(new FourthLevelCharacteristic());
        levelCharacteristics.add(new FifthLevelCharacteristic());
        this.terminal = terminal;
        this.maxLevelAmount = maxLevelAmount;
        this.player = generateRandomPlayer();
    }

    public LevelGenerator(String filePath, Terminal terminal) {
        this.filesPath = Optional.of(filePath);
        this.terminal = terminal;
        this.maxLevelAmount = Objects.requireNonNull(new File(System.getProperty("user.dir") + "/" + filePath).listFiles()).length;
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
        if (filesPath.isEmpty()) {
            return nextLevelRandom();
        }
        return nextLevelFromFile();
    }

    private GameCharacter getGameCharacterFromJson(JSONObject jsonCharacter) {
        CharacterType characterType = jsonCharacter.getEnum(CharacterType.class, "characterType");
        switch (characterType) {
            case POINTS:
                return new Points(jsonCharacter.getInt("numberOfPoints"));
            case OBSTACLE:
                return new Obstacle(jsonCharacter.getInt("destroyBonus"));
            case SHELTER_YELLOW:
            case SHELTER_PINK:
            case SHELTER_LAVENDER:
                return new Shelter(characterType);
            case INVENTORY:
                return new Inventory(jsonCharacter.getEnum(InventoryItem.class, "type"));
            case ENEMY_WEAK:
                JSONObject jsonShift = jsonCharacter.getJSONObject("shift");
                return new EnemyWeak(jsonCharacter.getInt("maxSteps"), new Coordinates(jsonShift.getInt("x"), jsonShift.getInt("y")));
            case ENEMY_STRONG:
                return new EnemyStrong();
            case PLAYER:
                JSONObject jsonCoordinates = jsonCharacter.getJSONObject("currentCoordinates");
                if (levelNumber == 0) {
                    player =  new Player(jsonCharacter.getInt("lives"), jsonCharacter.getInt("points"),
                                new Coordinates(jsonCoordinates.getInt("x"), jsonCoordinates.getInt("y")));
                } else {
                    player.setPoints(0);
                    player.getBackpack().clear();
                    player.setCurrentCoordinates(new Coordinates(jsonCoordinates.getInt("x"), jsonCoordinates.getInt("y")));
                }
                return player;
            default:
                return new Empty();
        }
    }

    private Level nextLevelFromFile() {
        String json = "";
        try {
            json = Files.readString(Path.of(System.getProperty("user.dir")+ "/" + filesPath.get() + "/level" + levelNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(json);
        int victoryPoints = jsonObject.getInt("victoryPoints");
        CharacterType realShelterType = jsonObject.getEnum(CharacterType.class, "realShelterType");
        JSONArray jsonArrayBoard = jsonObject.getJSONArray("board");
        int boardX = jsonArrayBoard.length();
        int boardY = jsonArrayBoard.getJSONArray(0).length();
        GameCharacter[][] board = new GameCharacter[boardX][boardY];
        Map<Enemy, Coordinates> enemies = new HashMap<>();
        for (int i = 0; i < boardX; i++) {
            JSONArray jsonArrayBoardRow = jsonArrayBoard.getJSONArray(i);
            if (jsonArrayBoardRow.length() != boardY) {
                throw new IllegalArgumentException("Invalid board size in file: ");
            }
            for (int j = 0; j < boardY; j++) {
                JSONObject jsonCharacter = jsonArrayBoardRow.getJSONObject(j);
                board[i][j] = getGameCharacterFromJson(jsonCharacter);
                if (board[i][j].getCharacterType() == CharacterType.ENEMY_WEAK
                        || board[i][j].getCharacterType() == CharacterType.ENEMY_STRONG) {
                    enemies.put((Enemy) board[i][j], new Coordinates(i, j));
                }
            }
        }
        GameScreenView gameScreenView = null;
        try {
            gameScreenView = new GameScreenViewConsole(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Level(board, gameScreenView, player, enemies, realShelterType, victoryPoints);
    }


    public Level nextLevelRandom() {
        player.setPoints(0);
        player.getBackpack().clear();
        LevelCharacteristic levelCharacteristic = levelCharacteristics.get(levelNumber);
        GameCharacter[][] board = new GameCharacter[levelCharacteristic.getX()][levelCharacteristic.getY()];
        Map<Enemy, Coordinates> enemies = new HashMap<>();
        int victoryPoints = 0;
        for (var entry: levelCharacteristic.getCharactersToPlace().entrySet()) {
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
                        victoryPoints += ((Points)characterToPlace).getNumberOfPoints();
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
