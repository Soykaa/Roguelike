package ru.hse.roguelike.model;

/**
 * Enum class for player action types.
 **/
public enum Action {
    /**
     * Destroy obstacle.
     **/
    DESTROY,
    /**
     * Move left.
     **/
    MOVE_LEFT,
    /**
     * Move right.
     **/
    MOVE_RIGHT,
    /**
     * Move up.
     **/
    MOVE_UP,
    /**
     * Move down.
     **/
    MOVE_DOWN,
    /**
     * Change equiption.
     **/
    CHANGE_EQUIPTION,
    /**
     * Unknown action.
     **/
    UNKNOWN_ACTION
}
