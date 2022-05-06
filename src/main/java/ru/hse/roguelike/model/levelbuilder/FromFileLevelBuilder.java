package ru.hse.roguelike.model.levelbuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.hse.roguelike.model.characters.CharacterType;
import ru.hse.roguelike.model.characters.Empty;
import ru.hse.roguelike.model.characters.mob.Mob;
import ru.hse.roguelike.model.characters.GameCharacter;
import ru.hse.roguelike.model.characters.Inventory;
import ru.hse.roguelike.model.characters.Obstacle;
import ru.hse.roguelike.model.characters.Player;
import ru.hse.roguelike.model.characters.Points;
import ru.hse.roguelike.model.characters.Shelter;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.characters.mob.factory.MobFactory;
import ru.hse.roguelike.model.InventoryItem;
import ru.hse.roguelike.model.Level;
import ru.hse.roguelike.model.characters.mob.factory.RedMobFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents level builder, which works with files.
 **/
public class FromFileLevelBuilder implements LevelBuilder {
    private final String fileDirectory;
    private int currentLevelNumber = 0;
    private Player player;
    private MobFactory enemyFactory = new RedMobFactory();

    /**
     * Creates new FromFileLevelBuilder instance.
     * Initialises config file directory with the given value.
     *
     * @param fileDirectory file directory
     **/
    public FromFileLevelBuilder(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Sets enemy factory.
     *
     * @param enemyFactory enemy factory
     **/
    @Override
    public void setEnemyFactory(MobFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    private GameCharacter getGameCharacterFromJson(JSONObject jsonCharacter, Coordinates coordinates) {
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
            case MOB_AGGRESSIVE:
                JSONObject jsonShift = jsonCharacter.getJSONObject("shift");
                return enemyFactory.createAggressiveMob(jsonCharacter.getInt("maxSteps"),
                        new Coordinates(jsonShift.getInt("x"), jsonShift.getInt("y")));
            case MOB_PASSIVE:
                return enemyFactory.createPassiveMob();
            case MOB_COWARD:
                jsonShift = jsonCharacter.getJSONObject("shift");
                return enemyFactory.createCowardMob(jsonCharacter.getInt("maxSteps"),
                        new Coordinates(jsonShift.getInt("x"), jsonShift.getInt("y")));
            case PLAYER:
                if (currentLevelNumber == 0) {
                    player = new Player(jsonCharacter.getInt("lives"), coordinates);
                } else {
                    player.setPoints(0);
                    player.getBackpack().clear();
                    player.setCurrentCoordinates(coordinates);
                }
                return player;
            default:
                return new Empty();
        }
    }

    /**
     * Creates level with the given player.
     *
     * @param player player
     * @return new level
     **/
    @Override
    public Level build(Player player) {
        this.player = player;
        String json = "";
        var path = Path.of(
                System.getProperty("user.dir") + File.separator + fileDirectory + File.separator + "level" + currentLevelNumber);
        if (!Files.exists(path)) {
            return null;
        }
        try {
            json = Files.readString(path);
        } catch (IOException e) {
            System.out.println("Problem with opening file: " + path);
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            int victoryPoints = jsonObject.getInt("victoryPoints");
            CharacterType realShelterType = jsonObject.getEnum(CharacterType.class, "realShelterType");
            JSONArray jsonArrayBoard = jsonObject.getJSONArray("board");
            int boardX = jsonArrayBoard.length();
            int boardY = jsonArrayBoard.getJSONArray(0).length();
            GameCharacter[][] board = new GameCharacter[boardX][boardY];
            Map<Mob, Coordinates> enemies = new HashMap<>();
            for (int i = 0; i < boardX; i++) {
                JSONArray jsonArrayBoardRow = jsonArrayBoard.getJSONArray(i);
                if (jsonArrayBoardRow.length() != boardY) {
                    throw new IllegalArgumentException("Invalid board size in file: " + path);
                }
                for (int j = 0; j < boardY; j++) {
                    JSONObject jsonCharacter = jsonArrayBoardRow.getJSONObject(j);
                    board[i][j] = getGameCharacterFromJson(jsonCharacter, new Coordinates(i, j));
                    if (board[i][j].getCharacterType() == CharacterType.MOB_AGGRESSIVE
                            || board[i][j].getCharacterType() == CharacterType.MOB_PASSIVE) {
                        enemies.put((Mob) board[i][j], new Coordinates(i, j));
                    }
                }
            }
            currentLevelNumber++;
            return new Level(board, this.player, enemies, realShelterType, victoryPoints);
        } catch (Exception e) {
            System.out.println("Problem with json in file " + path + ": " + e.getMessage());
            return null;
        }
    }
}