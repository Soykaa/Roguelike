package ru.hse.roguelike.model.level_characteristic;

import ru.hse.roguelike.model.character.CharacterType;
import ru.hse.roguelike.model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents level characteristics.
 **/
public abstract class LevelCharacteristic {
    private final int xSize;
    private final int ySize;
    private final Map<CharacterType, Integer> charactersToPlace;
    private final List<Coordinates> emptyCells = new ArrayList<>();
    private final CharacterType shelterType;


    /**
     * Creates new LevelCharacteristic instance.
     * Initialises fields with the given values and construct empty game board.
     *
     * @param xSize             board size on axis X
     * @param ySize             board size on axis Y
     * @param shelterType       shelter type
     * @param charactersToPlace map of game objects
     **/
    public LevelCharacteristic(int xSize, int ySize, CharacterType shelterType, Map<CharacterType, Integer> charactersToPlace) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.charactersToPlace = charactersToPlace;
        this.shelterType = shelterType;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                emptyCells.add(new Coordinates(i, j));
            }
        }
    }

    /**
     * Returns game board size on axis X.
     *
     * @return board size on axis X
     **/
    public int getX() {
        return xSize;
    }

    /**
     * Returns game board size on axis Y.
     *
     * @return board size on axis Y
     **/
    public int getY() {
        return ySize;
    }

    /**
     * Returns map of game objects with their IDs.
     *
     * @return map of game objects
     **/
    public Map<CharacterType, Integer> getCharactersToPlace() {
        return charactersToPlace;
    }

    /**
     * Returns shelter type.
     *
     * @return shelter type
     **/
    public CharacterType getShelterType() {
        return shelterType;
    }

    /**
     * Remove random empty cell from the list, connects it with the game object.
     *
     * @return selected cell
     **/
    public Coordinates getRandomCell() {
        Random rand = new Random();
        int cellIndex = rand.nextInt(emptyCells.size());
        Coordinates cell = emptyCells.get(cellIndex);
        emptyCells.remove(cellIndex);
        return cell;
    }

    /**
     * Determines if there are any empty cells.
     *
     * @return true if there are, false otherwise
     **/
    public boolean haveEmptyCells() {
        return emptyCells.size() > 0;
    }
}
