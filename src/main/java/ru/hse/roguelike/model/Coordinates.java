package ru.hse.roguelike.model;

/**
 * Game character coordinates.
 **/
public class Coordinates {
    private int x;
    private int y;

    /**
     * Creates new Coordinate instance.
     *
     * @param x coordinate on axis X
     * @param y coordinate on axis Y
     **/
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns coordinate on axis X.
     *
     * @return coordinate on axis X
     **/
    public int getX() {
        return x;
    }

    /**
     * Sets coordinate on axis X.
     *
     * @param x new coordinate in axis X
     **/
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns coordinate on axis Y.
     *
     * @return coordinate on axis Y
     **/
    public int getY() {
        return y;
    }

    /**
     * Sets coordinate on axis Y.
     *
     * @param y new coordinate in axis Y
     **/
    public void setY(int y) {
        this.y = y;
    }
}
