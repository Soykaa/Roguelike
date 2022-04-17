package ru.hse.roguelike.model.Characters.decorator;

import ru.hse.roguelike.model.Characters.Enemy;

public abstract class EnemyDecorator extends Enemy {
    protected final Enemy enemy;

    public EnemyDecorator(Enemy enemy) {
        super(enemy.getCharacterType(), enemy.getStrategy());
        this.enemy = enemy;
    }

}
