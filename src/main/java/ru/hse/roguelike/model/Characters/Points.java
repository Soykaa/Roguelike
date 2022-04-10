package ru.hse.roguelike.model.Characters;

public class Points extends GameCharacter {
    private final int numberOfPoints;

    public Points(int numberOfPoints) {
        super(CharacterType.POINTS);
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
