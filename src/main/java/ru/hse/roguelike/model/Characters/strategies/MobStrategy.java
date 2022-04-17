package ru.hse.roguelike.model.Characters.strategies;

import ru.hse.roguelike.model.Coordinates;

public abstract class MobStrategy {
    private int visibility = 0;
    private final int maxSteps;

    private int stepCount = 0;

    private final Coordinates shift;

    public MobStrategy(int visibility, int maxSteps, Coordinates shift) {
        this.visibility = visibility;
        this.maxSteps = maxSteps;
        this.shift = shift;
    }

    public MobStrategy(){
        maxSteps = 0;
        shift = null;
    }

    public abstract Coordinates makeNextMove(Coordinates mobCoordinates, Coordinates playerCoordinates);

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    protected Coordinates makeNextMoveUsual() {
        if (stepCount < maxSteps) {
            stepCount++;
            return shift;
        }
        stepCount = 0;
        shift.setX(-shift.getX());
        shift.setY(-shift.getY());
        return shift;
    }

    protected boolean canNotSeePlayer(Coordinates mobCoordinates, Coordinates playerCoordinates) {
        return Math.abs(mobCoordinates.getX() - playerCoordinates.getX()) > visibility ||
                Math.abs(mobCoordinates.getY() - playerCoordinates.getY()) > visibility;
    }

}
