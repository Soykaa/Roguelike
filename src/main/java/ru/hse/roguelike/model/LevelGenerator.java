package ru.hse.roguelike.model;

import org.json.JSONObject;
import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Characters.Empty;
import ru.hse.roguelike.model.Characters.Enemy;
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
import ru.hse.roguelike.model.levelbuilder.LevelBuilder;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameScreenView;

import org.json.JSONArray;

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

/**
 * Level generator.
 **/
public class LevelGenerator {
    private final AbstractViewFactory factory;
    private Player player;
    private final Random rand = new Random();
    private LevelBuilder levelBuilder;


    public LevelGenerator(AbstractViewFactory factory) {
        this.factory = factory;
    }

    public void setLevelBuilder(LevelBuilder levelBuilder) {
        player = generateRandomPlayer();
        this.levelBuilder = levelBuilder;
    }

    public Level getNextLevel() {
        if (rand.nextInt() % 2 == 0) {
            System.out.println("aaa");
            levelBuilder.setEnemyFactory(new RedEnemyFactory());
        } else {
            System.out.println("bbbb");
            levelBuilder.setEnemyFactory(new YellowEnemyFactory());
        }
        Level level = levelBuilder.build(player);
        if (level == null) {
            return null;
        }
        GameScreenView gameScreenView = null;
        try {
            gameScreenView = factory.createGameScreenView();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        level.setGameView(gameScreenView);
        return level;
    }



    private Player generateRandomPlayer() {
        int lives = rand.nextInt(10) + 1;
        return new Player(lives, 0);
    }

}
