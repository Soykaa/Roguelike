package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.EnemyStrong;
import ru.hse.roguelike.model.Characters.EnemyWeak;
import ru.hse.roguelike.model.Characters.GameCharacter;
import ru.hse.roguelike.model.Characters.Inventory;
import ru.hse.roguelike.model.Characters.Obstacle;
import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.Characters.Points;
import ru.hse.roguelike.model.Characters.Shelter;
import ru.hse.roguelike.model.LevelCharacteristics.LevelCharacteristic;
import ru.hse.roguelike.view.GameScreenView;
import ru.hse.roguelike.view.GameScreenViewConsole;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LevelGenerator {
    private Optional<String> filesPath;
    private int levelNumber = 0;
    private List<LevelCharacteristic> levelCharacteristics;
    GameScreenView gameView;

    public LevelGenerator(Optional<String> filesPath,
                          List<LevelCharacteristic> levelCharacteristics, GameScreenView gameView) {
        this.filesPath = filesPath;
        this.levelCharacteristics = levelCharacteristics;
        this.gameView = gameView;
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
        return true;
    }

    public Level nextLevel() {
        GameCharacter[][] board = new GameCharacter[3][3];
        var player = new Player(10, 2);
        var enemy = new EnemyWeak(2, new Coordinates(1, 0));
//        var enemy = new EnemyStrong();
        board[0][0] = player;
        board[1][0] = new Inventory(InventoryItem.PROTECTION);
        board[0][1] = new Obstacle();
        board[1][1] = new Points(6);
        board[0][2] = enemy;
        board[1][2] = new Empty();
        board[2][0] = new Inventory(InventoryItem.ATTACK);
        board[2][1] = new Shelter(CharacterType.SHELTER_LAVENDER);
        board[2][2] = new Empty();

        return new Level(board, gameView, player, Map.of(enemy, new Coordinates(0, 2)), CharacterType.SHELTER_PINK);
    }
}
