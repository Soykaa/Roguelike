package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

import java.util.Map;

/**
 * Represents characteristics of the second level.
 **/
public class SecondLevelCharacteristic extends LevelCharacteristic {
    /**
     * Creates new SecondLevelCharacteristic instance.
     * Calls parent constructor.
     **/
    public SecondLevelCharacteristic() {
        super(14, 12, CharacterType.SHELTER_LAVENDER, Map.of(
                        CharacterType.POINTS, 10,
                        CharacterType.PLAYER, 1,
                        CharacterType.ENEMY_AGGRESSIVE, 2,
                        CharacterType.ENEMY_PASSIVE, 1,
                        CharacterType.ENEMY_COWARD, 1,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 4,
                        CharacterType.SHELTER_PINK, 8,
                        CharacterType.INVENTORY, 1
                )
        );
    }
}
