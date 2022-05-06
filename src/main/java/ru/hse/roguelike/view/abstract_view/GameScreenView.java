package ru.hse.roguelike.view.abstract_view;

import java.io.IOException;

import ru.hse.roguelike.model.Backpack;
import ru.hse.roguelike.model.character.GameCharacter;

/**
 * Interface for game screen view.
 **/
public interface GameScreenView {
    /**
     * Shows game board.
     *
     * @param board board to show
     * @throws IOException in case of view error
     **/
    void showBoard(GameCharacter[][] board) throws IOException;

    /**
     * Moves game character.
     *
     * @param xFrom old coordinate on axis X
     * @param yFrom old coordinate on axis Y
     * @param xTo   new coordinate on axis X
     * @param yTo   new coordinate on axis Y
     * @throws IOException in case of view error
     **/
    void moveCharacter(int xFrom, int yFrom, int xTo, int yTo) throws IOException;

    /**
     * Sets message to show.
     *
     * @param message message to show
     * @throws IOException in case of view error
     **/
    void setMessage(String message) throws IOException;

    /**
     * Removes shown message.
     *
     * @throws IOException in case of view error
     **/
    void removeMessage() throws IOException;

    /**
     * Removes character from cell with passed coordinates.
     *
     * @param x coordinate on axis X
     * @param y coordinate on axis Y
     * @throws IOException in case of view error
     **/
    void removeCharacter(int x, int y) throws IOException;

    /**
     * Places character from cell with passed coordinates on the game board.
     *
     * @param character character to place
     * @param x         coordinate on axis X
     * @param y         coordinate on axis Y
     * @throws IOException in case of view error
     **/
    void placeCharacter(GameCharacter character, int x, int y) throws IOException;

    /**
     * Shows points.
     *
     * @param currentPoints current number of points
     * @param totalPoints   total number of points
     * @throws IOException in case of view error
     **/
    void showPoints(int currentPoints, int totalPoints) throws IOException;

    /**
     * Shows lives.
     *
     * @param lives number of lives
     * @throws IOException in case of view error
     **/
    void showLives(float lives) throws IOException;

    /**
     * Shows backpack with current active item.
     *
     * @param backpack backpack
     * @throws IOException in case of view error
     **/
    void showBackpack(Backpack backpack) throws IOException;

    /**
     * Shows player experience.
     *
     * @param currentExperience current experience
     * @param totalExperience   total experience
     * @throws IOException in case of view error
     **/
    void showExperience(int currentExperience, int totalExperience) throws IOException;
}
