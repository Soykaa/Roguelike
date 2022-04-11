package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

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
                        CharacterType.ENEMY_WEAK, 5,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 2,
                        CharacterType.SHELTER_PINK, 2,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
