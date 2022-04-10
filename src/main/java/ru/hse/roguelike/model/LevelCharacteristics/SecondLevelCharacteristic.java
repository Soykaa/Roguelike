package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

import java.util.Map;

public class SecondLevelCharacteristic extends LevelCharacteristic {
    public SecondLevelCharacteristic() {
        super(14, 12, CharacterType.SHELTER_LAVENDER, Map.of(
                        CharacterType.POINTS, 10,
                        CharacterType.PLAYER, 1,
                        CharacterType.ENEMY_WEAK, 2,
                        CharacterType.ENEMY_STRONG, 1,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 4,
                        CharacterType.SHELTER_PINK, 8,
                        CharacterType.INVENTORY, 1
                )
        );
    }
}
