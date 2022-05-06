package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

import java.util.Map;

/**
 * Represents characteristics of the fourth level.
 **/
public class FourthLevelCharacteristic extends LevelCharacteristic {
    /**
     * Creates new FourthLevelCharacteristic instance.
     * Calls parent constructor.
     **/
    public FourthLevelCharacteristic() {
        super(22, 16, CharacterType.SHELTER_LAVENDER, Map.of(
                        CharacterType.POINTS, 10,
                        CharacterType.PLAYER, 1,
                        CharacterType.MOB_AGGRESSIVE, 4,
                        CharacterType.MOB_PASSIVE, 2,
                        CharacterType.MOB_COWARD, 2,
                        CharacterType.OBSTACLE, 25,
                        CharacterType.SHELTER_LAVENDER, 4,
                        CharacterType.SHELTER_PINK, 4,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
