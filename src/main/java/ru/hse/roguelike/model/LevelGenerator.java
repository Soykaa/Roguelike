package ru.hse.roguelike.model;

import ru.hse.roguelike.model.LevelCharacteristics.LevelCharacteristic;

import java.util.List;
import java.util.Optional;

public class LevelGenerator {
    private Optional<String> filesPath;
    private int levelNumber;
    private List<LevelCharacteristic> levelCharacteristics;

    public LevelGenerator(Optional<String> filesPath, int levelNumber,
                          List<LevelCharacteristic> levelCharacteristics) {
        this.filesPath = filesPath;
        this.levelNumber = levelNumber;
        this.levelCharacteristics = levelCharacteristics;
    }

    public Optional<String> getFilesPath() {
        return filesPath;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public List<LevelCharacteristic> getLevelCharacteristics() {
        return levelCharacteristics;
    }

    public boolean hasNextLevel() {
        throw new UnsupportedOperationException();
    }

    public Level nextLevel() {
        throw new UnsupportedOperationException();
    }
}
