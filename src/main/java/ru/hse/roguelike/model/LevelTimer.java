package ru.hse.roguelike.model;

public class LevelTimer {
    private int secondsStep;
    private Runnable runnable;

    public int getSecondsStep() {
        return secondsStep;
    }

    public void setSecondsStep(int secondsStep) {
        this.secondsStep = secondsStep;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void startCountDown() {
        throw new UnsupportedOperationException();
    }
}
