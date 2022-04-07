package ru.hse.roguelike.model.LevelCharacteristics;

import ru.hse.roguelike.model.Characters.CharacterType;

import java.util.Map;

public class FourthLevelCharacteristic extends LevelCharacteristic {
    public FourthLevelCharacteristic() {
        super(22, 16, CharacterType.SHELTER_LAVENDER, Map.of(
                        CharacterType.POINTS, 10,
                        CharacterType.PLAYER, 1,
                        CharacterType.ENEMY_WEAK, 7,
                        CharacterType.ENEMY_STRONG, 3,
                        CharacterType.OBSTACLE, 20,
                        CharacterType.SHELTER_LAVENDER, 4,
                        CharacterType.SHELTER_PINK, 4,
                        CharacterType.INVENTORY, 2
                )
        );
    }
}
