package ru.hse.roguelike.model.Characters;

/**
 * Represents points.
 **/
public class Points extends GameCharacter {
    private final int numberOfPoints;

    /**
     * Creates new Points instance.
     * Calls parent constructor.
     * Initialises number of points with the given value.
     *
     * @param numberOfPoints points number
     **/
    public Points(int numberOfPoints) {
        super(CharacterType.POINTS);
        this.numberOfPoints = numberOfPoints;
    }

    /**
     * Returns number of points.
     *
     * @return number of points
     **/
    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
