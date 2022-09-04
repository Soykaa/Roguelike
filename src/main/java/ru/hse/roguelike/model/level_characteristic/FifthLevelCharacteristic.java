package ru.hse.roguelike.model.level_characteristic;

import ru.hse.roguelike.model.character.CharacterType;

import java.util.Map;

/**
 * Represents characteristics of the fifth level.
 **/
public class FifthLevelCharacteristic extends LevelCharacteristic {
    /**
     * Creates new FifthLevelCharacteristic instance.
     * Calls parent constructor.
     **/
    public FifthLevelCharacteristic() {
        super(26, 18, CharacterType.SHELTER_LAVENDER, Map.of(
                        CharacterType.POINTS, 10,
                        CharacterType.PLAYER, 1,
                        CharacterType.MOB_AGGRESSIVE, 5,
                        CharacterType.MOB_PASSIVE, 5,
                        CharacterType.MOB_COWARD, 3,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 2,
                        CharacterType.SHELTER_PINK, 2,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
