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
                        CharacterType.ENEMY_AGGRESSIVE, 7,
                        CharacterType.ENEMY_PASSIVE, 3,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 4,
                        CharacterType.SHELTER_PINK, 4,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
