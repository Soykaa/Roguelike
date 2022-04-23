package ru.hse.roguelike.model;

import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.levelbuilder.LevelBuilder;
import ru.hse.roguelike.view.abstract_view.AbstractViewFactory;
import ru.hse.roguelike.view.abstract_view.GameScreenView;

import java.io.IOException;
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
            levelBuilder.setEnemyFactory(new RedEnemyFactory());
        } else {
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
