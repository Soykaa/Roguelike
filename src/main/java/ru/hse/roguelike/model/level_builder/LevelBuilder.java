package ru.hse.roguelike.model.level_builder;

import ru.hse.roguelike.model.character.Player;
import ru.hse.roguelike.model.character.mob.factory.MobFactory;
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
     * Sets mob factory.
     *
     * @param mobFactory mob factory
     **/
    void setMobFactory(MobFactory mobFactory);
}
