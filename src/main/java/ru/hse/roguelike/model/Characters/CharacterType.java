package ru.hse.roguelike.model.Characters;

/**
 * Enum class for character types.
 **/
public enum CharacterType {
    /**
     * Aggressive enemy.
     **/
    ENEMY_AGGRESSIVE,
    /**
     * Passive enemy.
     **/
    ENEMY_PASSIVE,
    /**
     * Coward enemy.
     **/
    ENEMY_COWARD,
    /**
     * Obstacle.
     **/
    OBSTACLE,
    /**
     * Empty cell.
     **/
    EMPTY,
    /**
     * First type of shelter.
     **/
    SHELTER_LAVENDER,
    /**
     * Second type of shelter.
     **/
    SHELTER_YELLOW,
    /**
     * Third type of shelter.
     **/
    SHELTER_PINK,
    /**
     * Game points.
     **/
    POINTS,
    /**
     * Inventory.
     **/
    INVENTORY,
    /**
     * Player.
     **/
    PLAYER
}
