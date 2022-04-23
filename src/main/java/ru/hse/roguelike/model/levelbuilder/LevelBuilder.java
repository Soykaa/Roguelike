package ru.hse.roguelike.model.levelbuilder;

import ru.hse.roguelike.model.Characters.Player;
import ru.hse.roguelike.model.EnemyFactory;
import ru.hse.roguelike.model.Level;

public interface LevelBuilder {
    Level build(Player player);
    void setEnemyFactory(EnemyFactory enemyFactory);
}
