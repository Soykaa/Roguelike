package ru.hse.roguelike.model.character;

/**
 * Enum class for character types.
 **/
public enum CharacterType {
    /**
     * Aggressive mob.
     **/
    MOB_AGGRESSIVE,
    /**
     * Passive mob.
     **/
    MOB_PASSIVE,
    /**
     * Coward mob.
     **/
    MOB_COWARD,
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
