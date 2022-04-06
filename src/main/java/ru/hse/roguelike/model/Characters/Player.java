package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.Backpack;
import ru.hse.roguelike.model.Coordinates;

public class Player extends GameCharacter {
    private int lives;
    private int points;
    private Coordinates currentCoordinates = new Coordinates(0, 0);
    private final Backpack backpack = new Backpack();

    public Player(int lives, int points) {
        super(CharacterType.PLAYER);
        this.lives = lives;
        this.points = points;
    }

    public int getLives() {
        return lives;
    }

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void increasePoints(int points) {
        this.points += points;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void decreaseLives(int delta) {
        this.lives -= delta;
    }

    public int getPoints() {
        return points;
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    boolean canAttack() {
        throw new UnsupportedOperationException();
    }
}
