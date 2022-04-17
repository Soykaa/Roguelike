package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

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
                        CharacterType.ENEMY_AGGRESSIVE, 10,
                        CharacterType.ENEMY_PASSIVE, 5,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 2,
                        CharacterType.SHELTER_PINK, 2,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
