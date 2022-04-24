package ru.hse.roguelike.model.levelbuilder;

import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.EnemyFactory;
import ru.hse.roguelike.model.Level;

/**
 * Interface for level builder.
 **/
public interface LevelBuilder {
    /**
     * Creates level with the given player.
     *
     * @param player player
     * @return new level
     **/
    Level build(Player player);

    /**
     * Sets enemy factory.
     *
     * @param enemyFactory enemy factory
     **/
    void setEnemyFactory(EnemyFactory enemyFactory);
}
