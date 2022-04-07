package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;
import ru.hse.roguelike.model.Coordinates;
import ru.hse.roguelike.model.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class LevelCharacteristic {
    private final int xSize;
    private final int ySize;
    private final Map<CharacterType, Integer> charactersToPlace;
    private final List<Coordinates> emptyCells = new ArrayList<>();
    private final CharacterType shelterType;


    public LevelCharacteristic(int xSize, int ySize, CharacterType shelterType, Map<CharacterType, Integer> charactersToPlace) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.charactersToPlace = charactersToPlace;
        this.shelterType = shelterType;
        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++) {
                emptyCells.add(new Coordinates(i, j));
            }
        }
    }

    public int getX() {
        return xSize;
    }

    public int getY() {
        return ySize;
    }

    public Map<CharacterType, Integer> getCharactersToPlace() {
        return charactersToPlace;
    }

    public List<Coordinates> getEmptyCells() {
        return emptyCells;
    }

    public CharacterType getShelterType() {
        return shelterType;
    }

    public Coordinates getRandomCell() {
        Random rand = new Random();
        int cellIndex = rand.nextInt(emptyCells.size());
        var cell = emptyCells.get(cellIndex);
        emptyCells.remove(cellIndex);
        return cell;
    }

    public boolean haveEmptyCells() {
        return emptyCells.size() > 0;
    }
}
