package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

import java.util.Map;

public class FirstLevelCharacteristic extends LevelCharacteristic {
    public FirstLevelCharacteristic() {
        super(10, 10, CharacterType.SHELTER_LAVENDER, Map.of(
                                                                        CharacterType.POINTS, 10,
                                                                        CharacterType.PLAYER, 1,
                                                                        CharacterType.ENEMY_WEAK, 1,
                                                                        CharacterType.OBSTACLE, 20,
                                                                        CharacterType.SHELTER_LAVENDER, 1,
                                                                        CharacterType.SHELTER_PINK, 1,
                                                                        CharacterType.INVENTORY, 1
                                                                )
        );
    }
}
