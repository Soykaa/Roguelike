package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Backpack;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.InventoryItem;

/**
 * Represents player.
 **/
public class Player extends GameCharacter {
    private int experienceIncreaseForNextLevel = 5;
    private int waitForConfusion = 5;
    private int lives;
    private int points = 0;
    private int currentWait = 0;
    private int experience = 0;

    private Coordinates currentCoordinates = new Coordinates(0, 0);
    private final Backpack backpack = new Backpack();

    /**
     * Creates new Player instance.
     * Calls parent constructor.
     * Initialises lives with the given value.
     *
     * @param lives lives
     **/
    public Player(int lives, int experience) {
        super(CharacterType.PLAYER);
        this.lives = lives;
        this.experience = experience;
    }

    /**
     * Creates new Player instance.
     * Calls parent constructor.
     * Initialises lives and player coordinates with the given values.
     *
     * @param lives       lives
     * @param coordinates player coordinates
     **/
    public Player(int lives, Coordinates coordinates) {
        super(CharacterType.PLAYER);
        this.lives = lives;
        this.currentCoordinates = coordinates;
    }

    /**
     * Returns lives.
     *
     * @return number of lives
     **/
    public int getLives() {
        return lives;
    }

    /**
     * Returns current player coordinates.
     *
     * @return player coordinates
     **/
    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    /**
     * Increases points.
     *
     * @param points delta of points to increase current value
     **/
    public void increasePoints(int points) {
        this.points += points;
    }

    /**
     * Returns player backpack.
     *
     * @return backpack
     **/
    public Backpack getBackpack() {
        return backpack;
    }

    /**
     * Decreases lives.
     *
     * @param delta delta of lives to increase current value
     **/
    public void decreaseLives(int delta) {
        this.lives -= delta;
    }

    /**
     * Returns points.
     *
     * @return number of points
     **/
    public int getPoints() {
        return points;
    }

    /**
     * Sets new current coordinates.
     *
     * @param currentCoordinates new coordinates
     **/
    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    /**
     * Sets new number of points.
     *
     * @param points new number of points
     **/
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Determines if the game object can be destroyed by player.
     *
     * @return true if the object can be destroyed by player, false otherwise
     **/
    public boolean canDestroy() {
        return backpack.getActiveItem().getType() == InventoryItem.DESTROY;
    }

    public boolean canConfuse() {
        return currentWait == 0 && backpack.getActiveItem().getType() == InventoryItem.CONFUSION;
    }

    public void confuse() {
        currentWait = waitForConfusion;
        backpack.makeUnusable(InventoryItem.CONFUSION);
    }

    public int getExperienceIncreaseForNextLevel() {
        return experienceIncreaseForNextLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void decreaseWaitForConfusion() {
        if (currentWait > 0) {
            currentWait--;
            return;
        }
        backpack.makeUsable(InventoryItem.CONFUSION);
    }

    public void increaseExperience(int delta) {
        experience += delta;
        if (experience >= experienceIncreaseForNextLevel) {
            lives += 6;
            experience = 0;
            experienceIncreaseForNextLevel += 5;
            waitForConfusion--;
        }
    }
}
