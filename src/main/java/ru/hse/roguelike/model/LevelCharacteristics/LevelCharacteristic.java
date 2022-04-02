package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Coordinates;

import java.util.List;
import java.util.Map;

public abstract class LevelCharacteristic {
    private int[][] board;
    private Map<Integer, Character> characters;
    private List<Integer> charactersToPlace;
    private List<Coordinates> emptyCells;

    public LevelCharacteristic(int[][] board, Map<Integer, Character> characters,
                               List<Integer> charactersToPlace, List<Coordinates> emptyCells) {
        this.board = board;
        this.characters = characters;
        this.charactersToPlace = charactersToPlace;
        this.emptyCells = emptyCells;
    }

    public int[][] getBoard() {
        return board;
    }

    public Map<Integer, Character> getCharacters() {
        return characters;
    }

    public List<Integer> getCharactersToPlace() {
        return charactersToPlace;
    }

    public List<Coordinates> getEmptyCells() {
        return emptyCells;
    }
}
