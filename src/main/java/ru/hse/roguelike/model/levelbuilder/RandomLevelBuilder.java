package ru.hse.roguelike.model.levelbuilder;

import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.Enemy;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Inventory;
import ru.hse.roguelike.model.Characters.Obstacle;
import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.Characters.Points;
import ru.hse.roguelike.model.Characters.Shelter;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.EnemyFactory;
import ru.hse.roguelike.model.InventoryItem;
import ru.hse.roguelike.model.Level;
import ru.hse.roguelike.model.LevelCharacteristics.FirstLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.FourthLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.LevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.SecondLevelCharacteristic;
import ru.hse.roguelike.model.LevelCharacteristics.ThirdLevelCharacteristic;
import ru.hse.roguelike.model.RedEnemyFactory;
import ru.hse.roguelike.model.YellowEnemyFactory;
import ru.hse.roguelike.view.abstract_view.GameScreenView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomLevelBuilder implements LevelBuilder {

    private final List<LevelCharacteristic> levelCharacteristics = List.of(new FirstLevelCharacteristic(),
                                                                            new SecondLevelCharacteristic(),
                                                                            new ThirdLevelCharacteristic(),
                                                                            new FourthLevelCharacteristic(),
                                                                            new FirstLevelCharacteristic());

    private int currentLevelNumber = 0;

    private EnemyFactory enemyFactory = new YellowEnemyFactory();
    private final Random rand = new Random();

    @Override
    public void setEnemyFactory(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    private Enemy generateRandomEnemy(CharacterType enemyType) {
        int maxStep = rand.nextInt(4) + 1;
        int randomShift = rand.nextInt(4);
        List<Coordinates> shifts = List.of(new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1));
        switch (enemyType) {
            case ENEMY_AGGRESSIVE:
                return enemyFactory.createAggressiveEnemy(maxStep, shifts.get(randomShift));
            case ENEMY_COWARD:
                return enemyFactory.createCowardEnemy(maxStep, shifts.get(randomShift));
            default:
                return enemyFactory.createPassiveEnemy(maxStep, shifts.get(randomShift));
        }
    }

    private Points generatePoints() {
        int points = rand.nextInt(9) + 1;
        return new Points(points);
    }

    @Override
    public Level build(Player player) {
        if (currentLevelNumber >= levelCharacteristics.size()) {
            return null;
        }
        player.setPoints(0);
        player.getBackpack().clear();
        LevelCharacteristic levelCharacteristic = levelCharacteristics.get(currentLevelNumber);
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
                    case ENEMY_PASSIVE:
                    case ENEMY_AGGRESSIVE:
                    case ENEMY_COWARD:
                        var enemy = generateRandomEnemy(entry.getKey());
                        characterToPlace = enemy;
                        enemies.put(enemy, coordinates);
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
        currentLevelNumber++;
        return new Level(board, player, enemies, levelCharacteristic.getShelterType(), victoryPoints);
    }
}
