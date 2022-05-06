package ru.hse.roguelike.model.levelbuilder;

import ru.hse.roguelike.model.characters.Player;
import ru.hse.roguelike.model.characters.mob.factory.MobFactory;
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
    void setEnemyFactory(MobFactory enemyFactory);
}
