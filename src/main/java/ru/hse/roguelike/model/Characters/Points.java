package ru.hse.roguelike.model.Characters;

import ru.hse.roguelike.model.CharacterType;

public class Points extends GameCharacter {
    private int numberOfPoints;
    public Points(int numberOfPoints) {
        super(CharacterType.POINTS);
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
