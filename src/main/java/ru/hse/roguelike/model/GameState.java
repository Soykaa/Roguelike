package ru.hse.roguelike.model;

/**
 * Enum class for possible game states.
 **/
public enum GameState {
    /**
     * Player victory.
     **/
    VICTORY,
    /**
     * Player defeat.
     **/
    DEFEAT,
    /**
     * Game is running.
     **/
    IS_RUNNING,
    /**
     * Problem occurred while level generation or playing.
     **/
    PROBLEM_OCCURRED
}
