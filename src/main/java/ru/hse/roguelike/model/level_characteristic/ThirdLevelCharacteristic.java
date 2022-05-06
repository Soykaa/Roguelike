package ru.hse.roguelike.model.level_characteristic;

import ru.hse.roguelike.model.character.CharacterType;

import java.util.Map;

/**
 * Represents characteristics of the third level.
 **/
public class ThirdLevelCharacteristic extends LevelCharacteristic {
    /**
     * Creates new ThirdLevelCharacteristic instance.
     * Calls parent constructor.
     **/
    public ThirdLevelCharacteristic() {
        super(18, 14, CharacterType.SHELTER_LAVENDER, Map.of(
                        CharacterType.POINTS, 10,
                        CharacterType.PLAYER, 1,
                        CharacterType.MOB_AGGRESSIVE, 2,
                        CharacterType.MOB_PASSIVE, 2,
                        CharacterType.MOB_COWARD, 1,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 2,
                        CharacterType.SHELTER_PINK, 2,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
